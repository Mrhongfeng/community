package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * @since 2019-06-10
 */
@TableName("authorityapplication")
public class Authorityapplication extends Model<Authorityapplication> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 申请者Id
     */
    private String openId;
    /**
     * 申请身份
     */
    @TableField("target_auth")
    private Integer targetAuth;
    /**
     * 凭证路径
     */
    private String path;
    /**
     * 申请理由
     */
    private String content;
    /**
     * 申请状态
     */
    private Integer state;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Integer getTargetAuth() {
        return targetAuth;
    }

    public void setTargetAuth(Integer targetAuth) {
        this.targetAuth = targetAuth;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Authorityapplication{" +
        "id=" + id +
        ", openId=" + openId +
        ", targetAuth=" + targetAuth +
        ", path=" + path +
        ", content=" + content +
        ", state=" + state +
        "}";
    }
}
