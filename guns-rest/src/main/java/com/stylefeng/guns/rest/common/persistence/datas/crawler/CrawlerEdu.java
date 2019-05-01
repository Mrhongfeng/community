package com.stylefeng.guns.rest.common.persistence.datas.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 *
 **Edu Crawler模块**
 *
 *
 **/
public class CrawlerEdu extends Crawler{

    public CrawlerEdu(String url){
        this.setUrl(url);
    }

    public void preparing(){
        try {
            this.setDoc(Jsoup.connect(this.getUrl()).get());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setLinks(((Element) this.getDoc()).select("a[href]"));

        //String title = doc.title();
        //System.out.println("title is: " + title);
    }

    private String title;

    private String time;

    private String contents;

    //标题
    public String title(Element link){
        this.setKey(0);
        String linkHref = link.attr("href");
        try {
            Document docdetails;
            if(linkHref.charAt(0)!='h'){
                docdetails = Jsoup.connect(this.getUrl()+linkHref).ignoreContentType(true).get();
            }
            else{
                docdetails = Jsoup.connect(linkHref).ignoreContentType(true).get();
            }
            title = docdetails.title();

            contents = docdetails.text();

            String []text1 = contents.split("发布时间");

            String []text2 = text1[1].split("浏览次数");

            text2[0] = text2[0].substring(1, text2[0].length());

            text2[0] = text2[0].trim();

            time = text2[0];

            text2[1] = text2[1].substring(1, text2[1].length());

            text2[1] = text2[1].trim();

            contents = text2[1];

            this.setKey(1);

            return title;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //时间
    public String time(){
        if(this.getKey() == 1)
            return time;
        else
            return null;
    }

    //内容
    public String contents(){
        if(this.getKey() == 1)
            return contents;
        else
            return null;
    }


    public static void main( String[] args ) throws IOException {


        CrawlerEdu edu;

        edu = new CrawlerEdu("http://jwc.usst.edu.cn/");

        edu.preparing();

        int k = 0;
        for (Element link : edu.getLinks()) {
            if (k>=19&&k<=33){
                System.out.println("标题: " + edu.title(link));
                System.out.println("时间: " + edu.time());
                System.out.println("内容："+edu.contents());
            }
            k++;
        }


        // Document doc = Jsoup.connect("http://jwc.usst.edu.cn/").get();
        // //String title = doc.title();
        // //System.out.println("title is: " + title);
        // Elements links = ((Element) doc).select("a[href]");
        //int k=0;
        //for (Element link : links) {
        //    String linkHref = link.attr("href");
        //    //String linkText = link.text();
        //    if(k>=19&&k<=33)
        //    {
        //        //System.out.println("linktest:"+linkText+"  herf:"+linkHref+" baseuri  "+doc.baseUri());
        //        String href=linkHref;
        //        String detailsweb;
        //        if(href.charAt(0)!='h')
        //            detailsweb="http://jwc.usst.edu.cn"+href;
        //        else
        //            detailsweb=href;
        //        //System.out.println(detailsweb);//输出网址
        //        Document docdetails = Jsoup.connect(detailsweb).get();
        //        String titledetails = docdetails.title();
        //        String detailstext = docdetails.text();
        //
        //        String []time1 = detailstext.split("发布时间:");
        //        String []time2 = time1[1].split("浏览次数:");
        //
        //        System.out.println("标题: " + titledetails);
        //        System.out.println("时间: " + time2[0]);
        //        System.out.println("内容："+time2[1]);
        //        //System.out.println("内容："+detailstext);
        //    }
        //    k++;
        //    //获取详细页面
        //
        //}
        //
    }
}
