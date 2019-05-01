package com.stylefeng.guns.modular.system.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Community;
import com.stylefeng.guns.modular.system.service.ICommunityService;

/**
 * 社区活动管理控制器
 *
 * @author fengshuonan
 * @Date 2019-05-01 14:04:40
 */
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    private String PREFIX = "/system/community/";

    @Autowired
    private ICommunityService communityService;

    /**
     * 跳转到社区活动管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "community.html";
    }

    /**
     * 跳转到添加社区活动管理
     */
    @RequestMapping("/community_add")
    public String communityAdd() {
        return PREFIX + "community_add.html";
    }

    /**
     * 跳转到修改社区活动管理
     */
    @RequestMapping("/community_update/{communityId}")
    public String communityUpdate(@PathVariable Integer communityId, Model model) {
        Community community = communityService.selectById(communityId);
        model.addAttribute("item",community);
        LogObjectHolder.me().set(community);
        return PREFIX + "community_edit.html";
    }

    /**
     * 获取社区活动管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return communityService.selectList(null);
    }

    /**
     * 新增社区活动管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Community community) {
        communityService.insert(community);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除社区活动管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer communityId) {
        communityService.deleteById(communityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改社区活动管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Community community) {
        communityService.updateById(community);
        return super.SUCCESS_TIP;
    }

    /**
     * 社区活动管理详情
     */
    @RequestMapping(value = "/detail/{communityId}")
    @ResponseBody
    public Object detail(@PathVariable("communityId") Integer communityId) {
        return communityService.selectById(communityId);
    }
}
