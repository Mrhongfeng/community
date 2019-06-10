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
import com.stylefeng.guns.common.persistence.model.CommunityPicture;
import com.stylefeng.guns.modular.system.service.ICommunityPictureService;

/**
 * 社区活动图片控制器
 *
 * @author fengshuonan
 * @Date 2019-06-10 11:24:22
 */
@Controller
@RequestMapping("/community/communityPicture")
public class CommunityPictureController extends BaseController {

    private String PREFIX = "/system/communityPicture/";

    @Autowired
    private ICommunityPictureService communityPictureService;

    /**
     * 跳转到社区活动图片首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "communityPicture.html";
    }

    /**
     * 跳转到添加社区活动图片
     */
    @RequestMapping("/communityPicture_add")
    public String communityPictureAdd() {
        return PREFIX + "communityPicture_add.html";
    }

    /**
     * 跳转到修改社区活动图片
     */
    @RequestMapping("/communityPicture_update/{communityPictureId}")
    public String communityPictureUpdate(@PathVariable Integer communityPictureId, Model model) {
        CommunityPicture communityPicture = communityPictureService.selectById(communityPictureId);
        model.addAttribute("item",communityPicture);
        LogObjectHolder.me().set(communityPicture);
        return PREFIX + "communityPicture_edit.html";
    }

    /**
     * 获取社区活动图片列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return communityPictureService.selectList(null);
    }

    /**
     * 新增社区活动图片
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(CommunityPicture communityPicture) {
        communityPictureService.insert(communityPicture);
        return super.SUCCESS_TIP;
    }

    /**
     * 删除社区活动图片
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer communityPictureId) {
        communityPictureService.deleteById(communityPictureId);
        return SUCCESS_TIP;
    }

    /**
     * 修改社区活动图片
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(CommunityPicture communityPicture) {
        communityPictureService.updateById(communityPicture);
        return super.SUCCESS_TIP;
    }

    /**
     * 社区活动图片详情
     */
    @RequestMapping(value = "/detail/{communityPictureId}")
    @ResponseBody
    public Object detail(@PathVariable("communityPictureId") Integer communityPictureId) {
        return communityPictureService.selectById(communityPictureId);
    }
}
