package com.stylefeng.guns.rest.common.mythreads;

import com.stylefeng.guns.rest.common.persistence.datas.EduRefresh;

public class RunnableOfEduRefresh implements Runnable{
     private static long waitTime = 21600;

    private String streadName;

    public RunnableOfEduRefresh(String streadName){
        System.out.println("\t\t"+streadName+"正在初始化....");
        this.streadName = streadName;
    }
    @Override
    public void run() {
        while (true){
            EduRefresh eduRefresh = new EduRefresh();
            eduRefresh.refresh();
            try {
                Thread.sleep(waitTime*1000);
            }catch (InterruptedException e){
                System.err.println(e.toString());
            }
        }

    }
}
