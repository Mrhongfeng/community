package com.hongfeng.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private CommunityProperties communityProperties;

    @RequestMapping(value = "/say", method = RequestMethod.POST)
    public String say(@PathVariable("id") Integer id){
        return "0";
//        return "id:" + id;
//        return communityProperties.getSize();
    }
}
