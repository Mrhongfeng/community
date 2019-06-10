package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.persistence.model.Community;
import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.common.persistence.model.Authorityapplication;
import com.stylefeng.guns.modular.system.service.IAuthorityapplicationService;

/**
 * 角色申请管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-10 19:02:08
 */
@Controller
@RequestMapping("/community/authorityapplication")
public class AuthorityapplicationController extends BaseController {

    private String PREFIX = "/system/authorityapplication/";

    @Autowired
    private IAuthorityapplicationService authorityapplicationService;

    /**
     * 跳转到角色申请管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "authorityapplication.html";
    }

    /**
     * 跳转到添加角色申请管理
     */
    @RequestMapping("/authorityapplication_add")
    public String authorityapplicationAdd() {
        return PREFIX + "authorityapplication_add.html";
    }

    /**
     * 跳转到修改角色申请管理
     */
    @RequestMapping("/authorityapplication_update/{authorityapplicationId}")
    public String authorityapplicationUpdate(@PathVariable Integer authorityapplicationId, Model model) {
        Authorityapplication authorityapplication = authorityapplicationService.selectById(authorityapplicationId);
        model.addAttribute("item",authorityapplication);
        LogObjectHolder.me().set(authorityapplication);
        return PREFIX + "authorityapplication_edit.html";
    }

    /**
     * 获取角色申请管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return authorityapplicationService.selectList(new EntityWrapper<Authorityapplication>().eq("state","0"));
    }

    /**
     * 新增角色申请管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Authorityapplication authorityapplication) {
        authorityapplicationService.insert(authorityapplication);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除角色申请管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer authorityapplicationId) {
        authorityapplicationService.deleteById(authorityapplicationId);
        return SUCCESS_TIP;
    }

    /**
     * 修改角色申请管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Authorityapplication authorityapplication) {
        authorityapplicationService.updateById(authorityapplication);
        return super.SUCCESS_TIP;
    }

    /**
     * 角色申请管理详情
     */
    @RequestMapping(value = "/detail/{authorityapplicationId}")
    @ResponseBody
    public Object detail(@PathVariable("authorityapplicationId") Integer authorityapplicationId) {
        return authorityapplicationService.selectById(authorityapplicationId);
    }

    /**
     * 角色申请通过
     */
    @RequestMapping(value = "/permit/{Id}")
    @ResponseBody
    public Object permit(@PathVariable("Id") Integer Id) {
        Authorityapplication authorityapplication =authorityapplicationService.selectById(Id);
        authorityapplication.setState(1);
        authorityapplicationService.updateById(authorityapplication);
        return super.SUCCESS_TIP;
    }
}
