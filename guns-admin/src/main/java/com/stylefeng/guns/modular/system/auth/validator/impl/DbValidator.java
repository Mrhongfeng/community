package com.stylefeng.guns.modular.system.auth.validator.impl;


import com.stylefeng.guns.common.persistence.dao.UserMapper;
import com.stylefeng.guns.common.persistence.model.User;
import com.stylefeng.guns.modular.system.auth.validator.IReqValidator;
import com.stylefeng.guns.modular.system.auth.validator.dto.Credence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 账号密码验证
 *
 * @author fengshuonan
 * @date 2017-08-23 12:34
 */
@Service
public class DbValidator implements IReqValidator {     //连接数据库验证

    @Autowired
    UserMapper userMapper;

    @Override
    public boolean validate(Credence credence) {

        String userName = credence.getCredenceName();
        String password = credence.getCredenceCode();

        Map<String, Object> map = new HashMap<String, Object>();
        List<User> users = userMapper.selectByMap(map);
        Iterator<User> iusers = users.iterator();

        while (iusers.hasNext()) {
            User user = iusers.next();
            if (user.getAccount().equals(userName) && user.getPassword().equals(password)) return true;
        }

        return false;
    }
}


//List<User> users = userMapper.selectList(new EntityWrapper<User>().eq("userName", credence.getCredenceName()));
//if (users != null && users.size() > 0) {
//    return true;
//} else {
//       return false;
//}

