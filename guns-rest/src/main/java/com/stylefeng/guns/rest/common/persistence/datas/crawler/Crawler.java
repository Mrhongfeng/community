package com.stylefeng.guns.rest.common.persistence.datas.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawler {
    private int key = 0;

    private String url = null;

    private Document doc = null;

    private Elements links = null;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public Elements getLinks() {
        return links;
    }

    public void setLinks(Elements links) {
        this.links = links;
    }
}
