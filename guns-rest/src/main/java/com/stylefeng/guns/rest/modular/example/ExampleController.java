package com.stylefeng.guns.rest.modular.example;

import com.stylefeng.guns.rest.common.SimpleObject;
import com.stylefeng.guns.rest.common.persistence.dao.UserMapper;
import com.stylefeng.guns.rest.common.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Service
@Controller
@RequestMapping("/hello")
public class ExampleController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping("")
    public ResponseEntity hello(@RequestBody SimpleObject simpleObject) {
        System.out.println(simpleObject.getUser());
        User newUser = new User();

        newUser.setUserName(simpleObject.getName());
        newUser.setPassword(simpleObject.getTips());
        newUser.setAge(simpleObject.getAge());

        int tip = userMapper.insert(newUser);
        System.out.println(tip);
        if(tip == 1) return ResponseEntity.ok("请求成功!");
        else
            return  ResponseEntity.ok("请求失败！");
    }
}