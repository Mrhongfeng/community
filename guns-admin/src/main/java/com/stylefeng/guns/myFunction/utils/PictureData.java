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
     * 所属问题摘要
     */
    private String abstracts;
    /**
     * 所属问题内容
     */
    private String writeContests;


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

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getWriteContests() {
        return writeContests;
    }

    public void setWriteContests(String writeContests) {
        this.writeContests = writeContests;
    }
    @Override
    public String toString() {
        return "WallPicture{" +
                "id=" + id +
                ", fileName=" + fileName +
                ", filePath=" + filePath +
                ", abstracts=" + abstracts +
                ", writeContests=" + writeContests +
                "}";
    }
}
