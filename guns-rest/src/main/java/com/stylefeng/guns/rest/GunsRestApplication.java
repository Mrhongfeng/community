package com.stylefeng.guns.rest;

import com.stylefeng.guns.rest.common.mythreads.RunnableOfEduRefresh;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.stylefeng.guns"})
public class GunsRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(GunsRestApplication.class, args);


        //教务管理信息更新线程
        RunnableOfEduRefresh runnableOfEduRefresh =
                new RunnableOfEduRefresh("教务管理信息更新线程");
        Thread threadEdu = new Thread(runnableOfEduRefresh);
        threadEdu.start();
    }
}
