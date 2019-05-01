package com.stylefeng.guns.template;

import com.stylefeng.guns.generator.engine.SimpleTemplateEngine;
import com.stylefeng.guns.generator.engine.base.GunsTemplateEngine;
import com.stylefeng.guns.generator.engine.config.ContextConfig;

import java.io.IOException;

public class TemplateGenerator {

    public static void main(String[] args) throws IOException{
        ContextConfig contextConfig = new ContextConfig();
        contextConfig.setBizChName("教务公告管理");
        contextConfig.setBizEnName("Edu");
        contextConfig.setModuleName("Edu");
        contextConfig.setProjectPath("C:\\Users\\Medal姜总\\guns\\guns-admin");


        GunsTemplateEngine gunsTemplateEngine = new SimpleTemplateEngine();
        gunsTemplateEngine.setContextConfig(contextConfig);
        gunsTemplateEngine.start();
    }
}
