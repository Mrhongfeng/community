package com.stylefeng.guns.common.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author stylefeng123
 * @since 2019-06-10
 */
public class Community extends Model<Community> {

    private static final long serialVersionUID = 1L;

    /**
     * 活动id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 活动类别
     */
    private String acOrg;
    /**
     * 活动标题
     */
    private String acTitle;
    /**
     * 活动内容
     */
    private String acContent;
    /**
     * 活动地址
     */
    private String acLocation;
    /**
     * 活动开始时间
     */
    @TableField("acStart_time")
    private String acstartTime;
    /**
     * 活动结束时间
     */
    @TableField("acEnd_time")
    private String acendTime;
    /**
     * 人数上限
     */
    private String acThreshold;
    /**
     * 活动门槛
     */
    private String accredit;
    /**
     * 活动积分奖励
     */
    private Integer acBonus;
    /**
     * 活动状态
     */
    private Integer acState;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcOrg() {
        return acOrg;
    }

    public void setAcOrg(String acOrg) {
        this.acOrg = acOrg;
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

    public String getAcLocation() {
        return acLocation;
    }

    public void setAcLocation(String acLocation) {
        this.acLocation = acLocation;
    }

    public String getAcstartTime() {
        return acstartTime;
    }

    public void setAcstartTime(String acstartTime) {
        this.acstartTime = acstartTime;
    }

    public String getAcendTime() {
        return acendTime;
    }

    public void setAcendTime(String acendTime) {
        this.acendTime = acendTime;
    }

    public String getAcThreshold() {
        return acThreshold;
    }

    public void setAcThreshold(String acThreshold) {
        this.acThreshold = acThreshold;
    }


    public String getAccredit() {
        return accredit;
    }

    public void setAccredit(String accredit) {
        this.accredit = accredit;
    }

    public Integer getAcBonus() {
        return acBonus;
    }

    public void setAcBonus(Integer acBonus) {
        this.acBonus = acBonus;
    }

    public Integer getAcState() {
        return acState;
    }

    public void setAcState(Integer acState) {
        this.acState = acState;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Community{" +
        "id=" + id +
        ", acOrg=" + acOrg +
        ", acTitle=" + acTitle +
        ", acContent=" + acContent +
        ", acLocation=" + acLocation +
        ", acstartTime=" + acstartTime +
        ", acendTime=" + acendTime +
        ", acThreshold=" + acThreshold +
        ", accredit=" + accredit +
        ", acBonus=" + acBonus +
        ", acState=" + acState +
        "}";
    }
}
