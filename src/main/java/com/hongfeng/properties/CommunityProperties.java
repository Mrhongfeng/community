package com.hongfeng.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.xml.soap.Text;

@Component
@ConfigurationProperties(prefix = "community")
public class CommunityProperties {

    private String actitle;

    private String accontent;

    private String pubTime;

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
