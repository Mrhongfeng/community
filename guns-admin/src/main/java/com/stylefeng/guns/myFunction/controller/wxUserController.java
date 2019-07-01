package com.stylefeng.guns.myFunction.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.persistence.dao.WxuserMapper;
import com.stylefeng.guns.common.persistence.model.Wxuser;
import com.stylefeng.guns.myFunction.utils.AesCbcUtil;
import com.stylefeng.guns.myFunction.utils.HttpRequest;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Controller
@RequestMapping(value = "/community/Wxuser")
public class wxUserController {

    private static final Logger logger = LoggerFactory.getLogger(wxUserController.class);

    @Autowired
    WxuserMapper wxusermapper;

    @ResponseBody
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.GET)
    public Map decodeUserInfo(String encryptedData, String iv, String code){

        Map map = new HashMap();
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }

        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wx6d60e24e49efc98e";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "b745636e0e9036dcd855d9a5dc2a3fb8";
        //授权（必填）
        String grant_type = "authorization_code";


        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.fromObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = JSONObject.fromObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("userName", userInfoJSON.get("userName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("authority", "0");
                userInfo.put("credit", userInfoJSON.get("credit"));
                map.put("userInfo", userInfo);
                System.out.println("map:::"+userInfo);
                //存下来
                List<Wxuser> wxusers = wxusermapper.selectList(new EntityWrapper<Wxuser>().eq("openId",openid));
                if(wxusers.isEmpty()){
                    Wxuser wxuser=new Wxuser();
                    wxuser.setOpenId(String.valueOf(userInfoJSON.get("openId")));
                    wxuser.setUserName(String.valueOf(userInfoJSON.get("userName")));
                    wxuser.setGender(String.valueOf(userInfoJSON.get("gender")));
                    wxuser.setAvatarUrl(String.valueOf( userInfoJSON.get("avatarUrl")));
                    wxuser.setCountry(String.valueOf(userInfoJSON.get("country")));
                    wxuser.setProvince(String.valueOf(userInfoJSON.get("province")));
                    wxuser.setCity(String.valueOf(userInfoJSON.get("city")));
                    wxuser.setAuthority(String.valueOf("0"));
                    wxuser.setCredit(String.valueOf("100"));
                    wxusermapper.insert(wxuser);
                    logger.info(wxuser.toString());
                }else if(wxusers.get(0).getAuthority().equals("0"))
                    map.put("Authority","0");
                else if(wxusers.get(0).getAuthority().equals("1"))
                    map.put("Authority","1");
                else if(wxusers.get(0).getAuthority().equals("2"))
                    map.put("Authority","2");
                else if(wxusers.get(0).getAuthority().equals("3"))
                    map.put("Authority","3");
                else if(wxusers.get(0).getAuthority().equals("4"))
                    map.put("Authority","4");
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }
}