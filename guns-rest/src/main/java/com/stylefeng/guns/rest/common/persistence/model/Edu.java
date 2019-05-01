package com.stylefeng.guns.rest.common.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;


import java.io.Serializable;

/**
 * <p>
 * 教务处公告
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-20
 */
public class Edu extends Model<Edu> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 排序
     */
    private Integer cid;
    /**
     * 教务公告标题
     */
    private String eaTitle;
    /**
     * 教务公告时间
     */
    private String eaTime;
    /**
     * 教务公告内容
     */
    private String eaCont;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getEaTitle() {
        return eaTitle;
    }

    public void setEaTitle(String eaTitle) {
        this.eaTitle = eaTitle;
    }

    public String getEaTime() {
        return eaTime;
    }

    public void setEaTime(String eaTime) {
        this.eaTime = eaTime;
    }

    public String getEaCont() {
        return eaCont;
    }

    public void setEaCont(String eaCont) {
        this.eaCont = eaCont;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Edu{" +
        "id=" + id +
        "，cid=" + cid +
        ", eaTitle=" + eaTitle +
        ", eaTime=" + eaTime +
        ", eaCont=" + eaCont +
        "}";
    }
}
