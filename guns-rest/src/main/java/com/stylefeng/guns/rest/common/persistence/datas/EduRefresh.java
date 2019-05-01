package com.stylefeng.guns.rest.common.persistence.datas;

import com.stylefeng.guns.rest.common.persistence.model.Edu;
import com.stylefeng.guns.rest.common.persistence.datas.crawler.CrawlerEdu;
import com.stylefeng.guns.rest.common.persistence.dao.EduMapper;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class EduRefresh {

    @Autowired
    EduMapper eduMapper;

    private CrawlerEdu edu;

    private List<Edu> newEdus;

    private List<Edu> oldEdus;

    private List<Edu> nowEdus;

    public static EduRefresh eduRefresh;

    @PostConstruct
    public void init() {
        eduRefresh = this;
    }

    public static void refresh(){

        //获取新的教务处信息
        eduRefresh.newEdus = new ArrayList<>();

        eduRefresh.edu = new CrawlerEdu("http://jwc.usst.edu.cn/");

        eduRefresh.edu.preparing();

        Map<String,Object> map = new HashMap<String,Object>();

        eduRefresh.oldEdus =  eduRefresh.eduMapper.selectByMap(map);

        int k = 0;
        for (Element link :  eduRefresh.edu.getLinks()) {
            if (k>=19&&k<=33 && eduRefresh.edu.time() != null && eduRefresh.edu.title(link) != null && eduRefresh.edu.contents() != null){
                Edu newEdu = new Edu();
                newEdu.setEaTitle(eduRefresh.edu.title(link));
                newEdu.setEaTime(eduRefresh.edu.time());
                newEdu.setEaCont(eduRefresh.edu.contents());
                eduRefresh.newEdus.add(newEdu);
                //System.out.println("标题: " + newEdu.getEaTitle());
                //System.out.println("时间: " + newEdu.getEaTime());
                //System.out.println("内容："+newEdu.getEaCont());
                //System.out.println(eduRefresh.newEdus.size());
            }
            k++;
        }

        //合并新旧教务处信息
        for(int i = eduRefresh.newEdus.size()-1; i >= 0; i--){
            Edu edu1 = eduRefresh.newEdus.get(i);
            if( eduRefresh.oldEdus.size() == 0){
                eduRefresh.eduMapper.insert(edu1);
            }else{
                for(int j = 0; j < eduRefresh.oldEdus.size(); j++){
                    if(edu1.getEaTitle().equals( eduRefresh.oldEdus.get(j).getEaTitle())){
                        break;
                    }
                    else if(j == ( eduRefresh.oldEdus.size()-1)){
                        eduRefresh.eduMapper.insert(edu1);
                    }
                }
            }
        }

        //刷新排序
        eduRefresh.nowEdus = eduRefresh.eduMapper.selectByMap(map);
        for(int i = eduRefresh.nowEdus.size()-1,j = 1;i >= 0 ; i--,j++){
                eduRefresh.nowEdus.get(i).setCid(j);
                eduRefresh.eduMapper.updateAllColumnById(eduRefresh.nowEdus.get(i));
        }
    }
}
