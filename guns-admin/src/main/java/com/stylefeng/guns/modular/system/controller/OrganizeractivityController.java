package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.persistence.model.Community;
import com.stylefeng.guns.common.persistence.model.Teacheractivity;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.service.ICommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Organizeractivity;
import com.stylefeng.guns.modular.system.service.IOrganizeractivityService;

import java.util.ArrayList;
import java.util.List;

/**
 * 组织者活动管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-10 11:24:53
 */
@Controller
@RequestMapping("/community/organizeractivity")
public class OrganizeractivityController extends BaseController {

    private String PREFIX = "/system/organizeractivity/";

    @Autowired
    private IOrganizeractivityService organizeractivityService;

    @Autowired
    private ICommunityService communityService;

    /**
     * 跳转到组织者活动管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "organizeractivity.html";
    }

    /**
     * 跳转到添加组织者活动管理
     */
    @RequestMapping("/organizeractivity_add")
    public String organizeractivityAdd() {
        return PREFIX + "organizeractivity_add.html";
    }

    /**
     * 跳转到修改组织者活动管理
     */
    @RequestMapping("/organizeractivity_update/{organizeractivityId}")
    public String organizeractivityUpdate(@PathVariable Integer organizeractivityId, Model model) {
        Organizeractivity organizeractivity = organizeractivityService.selectById(organizeractivityId);
        model.addAttribute("item",organizeractivity);
        LogObjectHolder.me().set(organizeractivity);
        return PREFIX + "organizeractivity_edit.html";
    }

    /**
     * 获取组织者活动管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return organizeractivityService.selectList(null);
    }

    /**
     * 新增组织者活动管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Organizeractivity organizeractivity) {
        organizeractivityService.insert(organizeractivity);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除组织者活动管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer organizeractivityId) {
        organizeractivityService.deleteById(organizeractivityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改组织者活动管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Organizeractivity organizeractivity) {
        organizeractivityService.updateById(organizeractivity);
        return super.SUCCESS_TIP;
    }

    /**
     * 组织者活动管理详情
     */
    @RequestMapping(value = "/detail/{organizeractivityId}")
    @ResponseBody
    public Object detail(@PathVariable("organizeractivityId") Integer organizeractivityId) {
        return organizeractivityService.selectById(organizeractivityId);
    }

    /**
     * 组织者组织活动列表
     */
    @RequestMapping(value = "/myorganization/{openId}")
    @ResponseBody
    public Object myclass(@PathVariable("openId") Integer openId) {
        List<Community> communities=new ArrayList<Community>();
        List<Organizeractivity> myrecords = organizeractivityService.selectList(new EntityWrapper<Organizeractivity>().eq("openid",openId));
        for(Organizeractivity organizeractivity1:myrecords){
            System.out.println(organizeractivity1.getAcId());
            Community community=communityService.selectOne(new EntityWrapper<Community>().eq("Id",organizeractivity1.getAcId()));
            communities.add(community);
        }
        return communities;
    }
}
