package com.stylefeng.guns.rest.common.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 社团活动公告
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-21
 */
public class College extends Model<College> {

    private static final long serialVersionUID = 1L;

    /**
     * 排序
     */
    private Integer id;
    /**
     * 社团活动主题
     */
    private String title;
    /**
     * 活动发布时间
     */
    private String time;
    /**
     * 活动发布者
     */
    private String author;
    /**
     * 所属社团
     */
    private String college;
    /**
     * 活动具体内容
     */
    private String contents;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "College{" +
        "id=" + id +
        ", title=" + title +
        ", time=" + time +
        ", author=" + author +
        ", college=" + college +
        ", contents=" + contents +
        "}";
    }
}
