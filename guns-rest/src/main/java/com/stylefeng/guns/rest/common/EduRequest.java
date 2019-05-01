package com.stylefeng.guns.rest.common;

import com.stylefeng.guns.rest.common.persistence.dao.EduMapper;

import com.stylefeng.guns.rest.common.persistence.model.Edu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;
import java.util.*;

/**
 * 常规控制器
 *
 * @author fengshuonan
 * @date 2017-08-23 16:02
 */
@Service
@Controller
@RequestMapping("/edu")
public class EduRequest {

    @Autowired
    EduMapper eduMapper;

    @RequestMapping("/all")
    public ResponseEntity alledu() {
        Map<String,Object> map = new HashMap<String,Object>();
        List<Edu> edu= eduMapper.selectByMap(map);

        Iterator<Edu> itr = edu.listIterator();
        Edu edu1;
        while(itr.hasNext()){
            edu1 = itr.next();
            edu1.setEaCont("");
        }

        Collections.reverse(edu);
        return ResponseEntity.ok(edu);
    }

    @RequestMapping("/selectById")
    public ResponseEntity idcollege(@RequestBody Edu edu) {
        edu = eduMapper.selectById(edu.getId());
        return ResponseEntity.ok(edu);
    }
}