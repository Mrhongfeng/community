package com.stylefeng.guns.rest.common;
import com.stylefeng.guns.rest.common.persistence.dao.CollegeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Service
@Controller
@RequestMapping("/college")
public class College {

    @Autowired
    CollegeMapper collegeMapper;

    @RequestMapping("")
    public ResponseEntity college() {
        Map<String,Object> map = new HashMap<String,Object>();
        return ResponseEntity.ok(collegeMapper.selectByMap(map));
    }
}

//return ResponseEntity.ok(collegeMapper.selectById(1));
// System.out.println(collegeMapper.selectByMap(map));