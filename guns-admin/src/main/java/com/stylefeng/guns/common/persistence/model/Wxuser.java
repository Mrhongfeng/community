package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2019-05-15
 */
@TableName("wxuser")
public class Wxuser extends Model<Wxuser> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 微信用户id
     */
    private String openId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 头像
     */
    private String avatarUrl;
    /**
     * 国家
     */
    private String country;
    /**
     * 省
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 身份
     */
    private String authority;
    /**
     * 积分
     */
    private String credit;


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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Wxuser{" +
        "id=" + id +
        ", openId=" + openId +
        ", userName=" + userName +
        ", gender=" + gender +
        ", avatarUrl=" + avatarUrl +
        ", country=" + country +
        ", province=" + province +
        ", city=" + city +
        ", authority=" + authority +
        ", credit=" + credit +
        "}";
    }
}
