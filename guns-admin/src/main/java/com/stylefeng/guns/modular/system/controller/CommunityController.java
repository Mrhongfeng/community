package com.stylefeng.guns.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.common.constant.Const;
import com.stylefeng.guns.common.persistence.dao.CommunityMapper;
import com.stylefeng.guns.common.persistence.dao.CommunityPictureMapper;
import com.stylefeng.guns.common.persistence.model.CommunityPicture;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.auth.converter.MultipartHttpConverter;
import com.stylefeng.guns.myFunction.utils.FileToolsUtil;
import com.stylefeng.guns.myFunction.utils.PictureData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.common.persistence.model.Community;
import com.stylefeng.guns.modular.system.service.ICommunityService;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * 社区活动管理控制器
 *
 * @author fengshuonan
 * @Date 2019-06-10 15:30:21
 */
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);
    private List<Community> community;
    public static final String ROOT = "upload-dir";
    private final ResourceLoader resourceLoader;

    @Autowired
    CommunityMapper communityMapper;

    @Autowired
    ICommunityService wallService;

    @Autowired
    CommunityPictureMapper communityPictureMapper;
    private String PREFIX = "/system/community/";

    @Autowired
    private ICommunityService communityService;

    public CommunityController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

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
        return communityService.selectList(new EntityWrapper<Community>().eq("acState","1"));
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

    /**
     * 社区活动根据活动名称及活动内容模糊查询
     */
    @RequestMapping(value = "/search")
    @ResponseBody
    public Object search(@RequestParam String keyword) {
        return communityService.search(keyword);
    }

    /**
     * 已结束活动
     */
    @RequestMapping(value = "/endlist")
    @ResponseBody
    public Object endlist() {
        return communityService.endlist();
    }

    /**
     * 进行中活动
     */
    @RequestMapping(value = "/holdinglist")
    @ResponseBody
    public Object holdinglist() {
        return communityService.holding();
    }

    /**
     * 待审核活动
     */
    @RequestMapping(value = "/verifylist")
    @ResponseBody
    public Object verifylist() {
        return communityService.selectList(new EntityWrapper<Community>().eq("acState","0"));
    }

    /**
     * 审核通过活动
     */
    @RequestMapping(value = "/permit/{communityId}")
    @ResponseBody
    public Object permit(@PathVariable("communityId") Integer communityId) {
        Community community =communityService.selectById(communityId);
        community.setAcState(1);
        communityService.updateById(community);
        return super.SUCCESS_TIP;
    }


    @RequestMapping("/upLoadPicture")
    public ResponseEntity upLoadPicture(@RequestParam(value = "object")String object,
                                        @RequestParam(value = "sign")String sign,
                                        MultipartHttpServletRequest request)throws Exception{
        PictureData pictureData = (PictureData)new MultipartHttpConverter().read(object,sign,PictureData.class);
        community = null;
        community = communityMapper.selectList(new EntityWrapper<Community>().
                eq("acTitle",pictureData.getAcTitle()).
                eq("acContent",pictureData.getAcContent()));
        if(community.isEmpty())
            return ResponseEntity.ok("The problem does not exist");
        String file_path = Const.FILE_PATH+Const.FILE_SEPARATOR+Const.IMAGE_WALL+ Const.FILE_SEPARATOR+ Calendar.getInstance().get(Calendar.YEAR);
        String file_path_full  = FileToolsUtil.fileUpload(request.getFile("first_image"),FileToolsUtil.createDiretory(file_path));
        CommunityPicture communityPicture = new CommunityPicture();
        communityPicture.setPicturePath(file_path_full);
        communityPicture.setPictureName(file_path_full.substring(file_path_full.lastIndexOf(Const.FILE_SEPARATOR)+1));
        communityPicture.setCommunityId(community.get(0).getId());
        logger.info("FileName: " + communityPicture.getPictureName());
        logger.info("FilePath: " + communityPicture.getPicturePath());
        logger.info("Abstracts: " + communityPicture.getCommunityId());
        return ResponseEntity.ok(communityMapper.insert(communityPicture));
    }

    @GetMapping("/downLoadPicture/{fileName:[a-zA-Z0-9\\.\\-\\_]+}")
    public ResponseEntity downLoadPicture(@PathVariable("fileName") String fileName ,
                                          HttpServletRequest request){
        String filePath = null;
        filePath = communityPictureMapper.selectList(new EntityWrapper<CommunityPicture>().eq("fileName",fileName)).get(0).getPicturePath();
        logger.info(filePath);
        String strDirPath = request.getSession().getServletContext().getRealPath("/");
        logger.info(strDirPath);
        String pp = request.getRequestURI();
        logger.info(pp);
        String path=request.getServletContext().getContextPath();
        logger.info(path);
        String realPath=request.getServletContext().getRealPath("/static");
        logger.info(realPath);
        strDirPath = strDirPath+"WEB-INF"+Const.FILE_SEPARATOR+"classes"+Const.FILE_SEPARATOR+"static"+Const.FILE_SEPARATOR+"upload";
        FileToolsUtil.fileToUpload(strDirPath,filePath);
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + strDirPath + Const.FILE_SEPARATOR + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/getPictureFileName")
    public ResponseEntity getPictureFilName(@RequestBody Community community){
        List<CommunityPicture> communityPictures = null;
        communityPictures = communityPictureMapper.selectList(
                new EntityWrapper<CommunityPicture>().
                        eq("communityId",community.getId()));
        if(communityPictures.isEmpty())
            return ResponseEntity.ok("NULL");
        else
            return ResponseEntity.ok(communityPictures.get(0).getPictureName());
    }


}
