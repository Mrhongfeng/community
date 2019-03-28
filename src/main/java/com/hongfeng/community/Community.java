package com.hongfeng.community;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.soap.Text;

@Entity
public class Community {

    @Id
    @GeneratedValue
    private Integer id;

    private String actitle;

    private String accontent;

    private String pubTime;

    public Community() {
    }

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
}
