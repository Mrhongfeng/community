package com.stylefeng.guns;

import com.alibaba.fastjson.JSON;
import com.stylefeng.guns.core.util.MD5Util;
import com.stylefeng.guns.modular.system.auth.converter.BaseTransferEntity;
import com.stylefeng.guns.modular.system.auth.security.impl.Base64SecurityAction;
import javafx.beans.property.SimpleObjectProperty;


/**
 * jwt测试
 *
 * @author fengshuonan
 * @date 2017-08-21 16:34
 */
public class DecryptTest {

    public static void main(String[] args) {

        String key = "mySecret";

        String compactJws = "eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJrYThuZTYiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTU1NzA2MjA4NywiaWF0IjoxNTU2NDU3Mjg3fQ.ZeKrSnUsStJguc2GyDKs7xc3TPZVPe63CGE9pGYR6sGdQdW1gqs6bbYP40Kz1M4IIsyfhewtHVeihhrfsvuGCg";

        String randomKey = "ka8ne6";

        SimpleObjectProperty simpleObjectProperty = new SimpleObjectProperty();
        simpleObjectProperty.setValue("hongfeng");


        String jsonString = JSON.toJSONString(simpleObjectProperty);
        String encode = new Base64SecurityAction().doAction(jsonString);
        String md5 = MD5Util.encrypt(encode + randomKey);

        BaseTransferEntity baseTransferEntity = new BaseTransferEntity();
        baseTransferEntity.setObject(encode);
        baseTransferEntity.setSign(md5);

        System.out.println(JSON.toJSONString(baseTransferEntity));

        //System.out.println("body = " + Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody());
        //System.out.println("header = " + Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getHeader());
        //System.out.println("signature = " + Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getSignature());
    }
}
