package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.persistence.model.Community;
import com.stylefeng.guns.common.persistence.model.Wxuser;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.service.ICommunityService;
import com.stylefeng.guns.modular.system.service.IWxuserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Wxuseractivity;
import com.stylefeng.guns.modular.system.service.IWxuseractivityService;

import java.util.ArrayList;
import java.util.List;

/**
 * useractivity控制器
 *
 * @author fengshuonan
 * @Date 2019-05-30 20:57:21
 */
@Controller
@RequestMapping("/community/wxuseractivity")
public class WxuseractivityController extends BaseController {

    private String PREFIX = "/system/wxuseractivity/";

    @Autowired
    private IWxuseractivityService wxuseractivityService;

    @Autowired
    private ICommunityService communityService;

    @Autowired
    private IWxuserService wxuserService;



    /**
     * 跳转到useractivity首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "wxuseractivity.html";
    }

    /**
     * 跳转到添加useractivity
     */
    @RequestMapping("/wxuseractivity_add")
    public String wxuseractivityAdd() {
        return PREFIX + "wxuseractivity_add.html";
    }

    /**
     * 跳转到修改useractivity
     */
    @RequestMapping("/wxuseractivity_update/{wxuseractivityId}")
    public String wxuseractivityUpdate(@PathVariable Integer wxuseractivityId, Model model) {
        Wxuseractivity wxuseractivity = wxuseractivityService.selectById(wxuseractivityId);
        model.addAttribute("item",wxuseractivity);
        LogObjectHolder.me().set(wxuseractivity);
        return PREFIX + "wxuseractivity_edit.html";
    }

    /**
     * 获取useractivity列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return wxuseractivityService.selectList(null);
    }

    /**
     * 新增useractivity
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Wxuseractivity wxuseractivity) {
//        wxuseractivity.setSign(0);
        wxuseractivityService.insert(wxuseractivity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除useractivity
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer wxuseractivityId) {
        wxuseractivityService.deleteById(wxuseractivityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改useractivity
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Wxuseractivity wxuseractivity) {
        wxuseractivityService.updateById(wxuseractivity);
        return super.SUCCESS_TIP;
    }

    /**
     * useractivity详情
     */
    @RequestMapping(value = "/detail/{wxuseractivityId}")
    @ResponseBody
    public Object detail(@PathVariable("wxuseractivityId") Integer wxuseractivityId) {
        return wxuseractivityService.selectById(wxuseractivityId);
    }

    /**
     * 获取学员人数
     */
    @RequestMapping(value = "/usercount/{wxuseractivityId}")
    @ResponseBody
    public Object usercount(@PathVariable Integer wxuseractivityId) {
        return wxuseractivityService.selectCount(new EntityWrapper<Wxuseractivity>().eq("acId",wxuseractivityId));
    }

    /**
     * 获取学员列表
     */
    @RequestMapping(value = "/userlist/{wxuseractivityId}")
    @ResponseBody
    public Object userlist(@PathVariable Integer wxuseractivityId) {
        return wxuseractivityService.selectList(new EntityWrapper<Wxuseractivity>().eq("acId",wxuseractivityId));
    }


    /**
     * 学员报名
     */
    @RequestMapping(value="/userenroll")
    @ResponseBody
    public Object enroll(Wxuseractivity wxuseractivity) {
        Wxuseractivity wxuseractivity1=wxuseractivityService.selectOne(new EntityWrapper<Wxuseractivity>().eq("acId",wxuseractivity.getAcId()).eq("openId",wxuseractivity.getOpenId()));
        Wxuser wxuser=wxuserService.selectOne(new EntityWrapper<Wxuser>().eq("openId",wxuseractivity.getOpenId()));
        if(wxuseractivity1!=null){
            return "您已报名，请勿重复报名";         //已存在记录，报名失败
        }else {
            Community community=communityService.selectOne(new EntityWrapper<Community>().eq("Id",wxuseractivity.getAcId()));
            int count=wxuseractivityService.selectCount(new EntityWrapper<Wxuseractivity>().eq("acId",wxuseractivity1.getAcId()));
            if(community.getAcThreshold()!="无上限") {
                if (count + 1 > Integer.parseInt(community.getAcThreshold())) {
                    return "人数已达上限";             //人数超过上限，报名失败
                }
            }
            if(Integer.parseInt(wxuser.getCredit())<Integer.parseInt(community.getAccredit())){
                return "您的积分低于报名要求";                 //积分低于门槛，报名失败
            }
        }
        wxuseractivityService.insert(wxuseractivity);
        return super.SUCCESS_TIP;
    }



    /**
     * 学员签到
     */
    @RequestMapping(value = "/sign")
    @ResponseBody
    public Object sign(Wxuseractivity wxuseractivity) {
        Wxuseractivity wxuseractivity1 =wxuseractivityService.selectOne(new EntityWrapper<Wxuseractivity>().eq("openid",wxuseractivity.getOpenId()).eq("acId",wxuseractivity.getAcId()).eq("sign","0"));
        wxuseractivity1.setSign(1);
        wxuseractivityService.updateById(wxuseractivity1);
        return super.SUCCESS_TIP;
    }

    /**
     * 我的活动汇总
     */
    @RequestMapping(value = "/myactivity//{openId}")
    @ResponseBody
    public Object myactivity(Wxuseractivity wxuseractivity) {
        List<Community> communities=new ArrayList<Community>();
        List<Wxuseractivity> myrecords = wxuseractivityService.selectList(new EntityWrapper<Wxuseractivity>().eq("openid",wxuseractivity.getOpenId()));
        for(Wxuseractivity wxuseractivity1:myrecords){
            System.out.println(wxuseractivity1.getAcId());
            Community community=communityService.selectOne(new EntityWrapper<Community>().eq("Id",wxuseractivity1.getAcId()));
            communities.add(community);
        }
        return communities;
    }

}
