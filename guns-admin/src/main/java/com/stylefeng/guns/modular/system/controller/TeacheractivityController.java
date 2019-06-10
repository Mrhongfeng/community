package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.persistence.model.Community;
import com.stylefeng.guns.common.persistence.model.Wxuser;
import com.stylefeng.guns.common.persistence.model.Wxuseractivity;
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
import com.stylefeng.guns.common.persistence.model.Teacheractivity;
import com.stylefeng.guns.modular.system.service.ITeacheractivityService;

import java.util.ArrayList;
import java.util.List;

/**
 * 导师活动管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-10 11:25:17
 */

@Controller
@RequestMapping("/community/teacheractivity")
public class TeacheractivityController extends BaseController {

    private String PREFIX = "/system/teacheractivity/";

    @Autowired
    private ITeacheractivityService teacheractivityService;

    @Autowired
    private ICommunityService communityService;

    @Autowired
    private IWxuserService wxuserService;

    /**
     * 跳转到导师活动管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "teacheractivity.html";
    }

    /**
     * 跳转到添加导师活动管理
     */
    @RequestMapping("/teacheractivity_add")
    public String teacheractivityAdd() {
        return PREFIX + "teacheractivity_add.html";
    }

    /**
     * 跳转到修改导师活动管理
     */
    @RequestMapping("/teacheractivity_update/{teacheractivityId}")
    public String teacheractivityUpdate(@PathVariable Integer teacheractivityId, Model model) {
        Teacheractivity teacheractivity = teacheractivityService.selectById(teacheractivityId);
        model.addAttribute("item",teacheractivity);
        LogObjectHolder.me().set(teacheractivity);
        return PREFIX + "teacheractivity_edit.html";
    }

    /**
     * 获取导师活动管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return teacheractivityService.selectList(null);
    }

    /**
     * 新增导师活动管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Teacheractivity teacheractivity) {
        teacheractivityService.insert(teacheractivity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除导师活动管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer teacheractivityId) {
        teacheractivityService.deleteById(teacheractivityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改导师活动管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Teacheractivity teacheractivity) {
        teacheractivityService.updateById(teacheractivity);
        return super.SUCCESS_TIP;
    }

    /**
     * 导师活动管理详情
     */
    @RequestMapping(value = "/detail/{teacheractivityId}")
    @ResponseBody
    public Object detail(@PathVariable("teacheractivityId") Integer teacheractivityId) {
        return teacheractivityService.selectById(teacheractivityId);
    }

    /**
     * 导师抢单
     */
    @RequestMapping(value="/teacherenroll")
    @ResponseBody
    public Object enroll(Teacheractivity teacheractivity) {
        Teacheractivity teacheractivity1=teacheractivityService.selectOne(new EntityWrapper<Teacheractivity>().eq("acId",teacheractivity.getAcId()));
        Wxuser wxuser=wxuserService.selectOne(new EntityWrapper<Wxuser>().eq("openId",teacheractivity.getOpenId()));
        if(teacheractivity1!=null){
            if(teacheractivity1.getOpenId().equals(wxuser.getId())){
                return "您已抢到此课程";      //已抢到，抢单失败
            }
            return "此课程已被其他教师抢走";         //已被抢
        }else if(Integer.parseInt(wxuser.getAuthority())==1){
            return "您的权限不够，无法抢单";         //权限不够，不能抢单
        }
        teacheractivityService.insert(teacheractivity);
        return super.SUCCESS_TIP;
    }

    /**
     * 导师教授课程列表
     */
    @RequestMapping(value = "/myclass/{openId}")
    @ResponseBody
    public Object myclass(@PathVariable("openId") Integer openId) {
        List<Community> communities=new ArrayList<Community>();
        List<Teacheractivity> myrecords = teacheractivityService.selectList(new EntityWrapper<Teacheractivity>().eq("openid",openId));
        for(Teacheractivity teacheractivity1:myrecords){
            System.out.println(teacheractivity1.getAcId());
            Community community=communityService.selectOne(new EntityWrapper<Community>().eq("Id",teacheractivity1.getAcId()));
            communities.add(community);
        }
        return communities;
    }




}
