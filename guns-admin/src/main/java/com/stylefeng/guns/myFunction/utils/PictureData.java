package com.stylefeng.guns.myFunction.utils;
/**
 * @Author: YunJieJiang
 * @Date: Created in 11:17 2018/10/29 0029
 */
public class PictureData{
    /**
     * 主键
     */
    private Integer id;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件路径
     */
    private String filePath;
    /**
     * 所属活动摘要
     */
    private String acTitle;
    /**
     * 所属问题内容
     */
    private String acContent;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAcTitle() {
        return acTitle;
    }

    public void setAcTitle(String acTitle) {
        this.acTitle = acTitle;
    }

    public String getAcContent() {
        return acContent;
    }

    public void setAcContent(String acContent) {
        this.acContent = acContent;
    }

    @Override
    public String toString() {
        return "WallPicture{" +
                "id=" + id +
                ", fileName=" + fileName +
                ", filePath=" + filePath +
                ", acTitle=" + acTitle +
                ", acContent=" + acContent +
                "}";
    }
}
