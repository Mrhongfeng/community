package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2019-05-01
 */
@TableName("community")
public class Community extends Model<Community> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String actitle;
    private String accontent;
    @TableField("pub_time")
    private String pubTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActitle() {
        return actitle;
    }

    public void setActitle(String actitle) {
        this.actitle = actitle;
    }

    public String getAccontent() {
        return accontent;
    }

    public void setAccontent(String accontent) {
        this.accontent = accontent;
    }

    public String getPubTime() {
        return pubTime;
    }

    public void setPubTime(String pubTime) {
        this.pubTime = pubTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Community{" +
        "id=" + id +
        ", actitle=" + actitle +
        ", accontent=" + accontent +
        ", pubTime=" + pubTime +
        "}";
    }
}
