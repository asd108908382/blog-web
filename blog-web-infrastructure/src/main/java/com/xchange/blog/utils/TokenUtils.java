package com.xchange.blog.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.cola.exception.Assert;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/2 17:04
 */
@Slf4j
public class TokenUtils {

    private static final String TOKEN_KEY = "hsdasd1";

    public static String generateToken(Long id) {
        Assert.notNull(id, "id is null");
        Map<String, Object> map = new HashMap<>();
        map.put("uid", id);
        map.put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
        return JWTUtil.createToken(map, TOKEN_KEY.getBytes());
    }

    public static boolean isExpire(String token) {
        try {
            Assert.notNull(token, "token is null");
            JWT jwt = JWTUtil.parseToken(token);
            String expireTime = jwt.getPayload("expire_time").toString();
            return System.currentTimeMillis() > Long.parseLong(expireTime);
        } catch (Exception e) {
            log.error("token is error", e);
            return true;
        }
    }

    public static Long getId(String token) {
        try {
            Assert.notNull(token, "token is null");
            JWT jwt = JWTUtil.parseToken(token);
            return Long.parseLong(jwt.getPayload("uid").toString());
        } catch (Exception e) {
            log.error("token is error", e);
            return null;
        }
    }
}
