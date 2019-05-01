package com.stylefeng.guns.modular.system.auth.converter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.support.HttpKit;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.common.exception.BizExceptionEnum;
import com.stylefeng.guns.config.properties.JwtProperties;
import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.modular.system.auth.security.DataSecurityAction;
import com.stylefeng.guns.modular.system.auth.util.JwtTokenUtil;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.io.IOException;
import java.lang.reflect.Type;

import java.io.IOException;

public class MultipartHttpConverter extends FastJsonHttpMessageConverter {
    private JwtProperties jwtProperties                 = SpringContextHolder.getBean(JwtProperties.class);
    private JwtTokenUtil jwtTokenUtil                   = SpringContextHolder.getBean(JwtTokenUtil.class);
    private DataSecurityAction dataSecurityAction       = SpringContextHolder.getBean(DataSecurityAction.class);


    public Object read(String o,String s,Type t) throws IOException, HttpMessageNotReadableException {
        //先转化成原始的对象
        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(o);
        baseTransferEntity.setSign(s);
        //校验签名
        String token = HttpKit.getRequest().getHeader(jwtProperties.getHeader()).substring(7);
        String md5KeyFromToken = jwtTokenUtil.getMd5KeyFromToken(token);

        String object = baseTransferEntity.getObject();
        String json = dataSecurityAction.unlock(object);
        String encrypt = MD5Util.encrypt(object + md5KeyFromToken);

        if (encrypt.equals(baseTransferEntity.getSign())) {
            System.out.println("签名校验成功!");
        } else {
            System.out.println("签名校验失败,数据被改动过!");
            throw new GunsException(BizExceptionEnum.SIGN_ERROR);
        }
        //校验签名后再转化成应该的对象
        return JSON.parseObject(json, t);
    }
}
