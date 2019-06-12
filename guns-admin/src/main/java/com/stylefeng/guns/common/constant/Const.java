package com.stylefeng.guns.common.constant;

import java.io.File;

/**
 * 系统常量
 *
 * @author fengshuonan
 * @date 2017年2月12日 下午9:42:53
 */
public interface Const {

    /**
     * 系统默认的管理员密码
     */
    String DEFAULT_PWD = "111111";

    /**
     * 管理员角色的名字
     */
    String ADMIN_NAME = "administrator";

    /**
     * 管理员id
     */
    Integer ADMIN_ID = 1;

    /**
     * 超级管理员角色id
     */
    Integer ADMIN_ROLE_ID = 1;

    /**
     * 接口文档的菜单名
     */
    String API_MENU_NAME = "接口文档";

    /**
     * 上传文件路径
     */
    String IMAGE_SAVE_PATH_Windows="C:\\Users\\honfus\\Desktop\\images";

    /**
     * linux上传文件目录
     */
     String IMAGE_SAVE_PATH_LINUX="/usr/local";

    /**
     * 系统文件路径
     */
     String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * 获取操作系统类型
     */
     String os = System.getProperty("os.name");

    /**
     * 获取当前存储路径
     */
     String FILE_PATH = Const.os.toLowerCase().indexOf("win")!=-1?Const.IMAGE_SAVE_PATH_Windows:Const.IMAGE_SAVE_PATH_LINUX;

    /**
     * 问题墙文件夹
     */
    String IMAGE_WALL = "wall";
}
