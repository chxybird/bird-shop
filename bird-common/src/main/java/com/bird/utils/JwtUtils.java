package com.bird.utils;

import io.jsonwebtoken.*;

import java.util.*;

/**
 * @Author lipu
 * @Date 2020/12/18 20:29
 * @Description JWT工具类
 */
public class JwtUtils {

    public static final Calendar TIME=Calendar.getInstance();
    /**
     * 密钥
     */
    public static final String KEY=Base64.getMimeEncoder().encodeToString("BIRD".getBytes());

    /**
     * @Author lipu
     * @Date 2020/12/18 20:40
     * @Description 创建jwt令牌
     */
    public static String initJwt(){
        //设置过期时间
        TIME.add(Calendar.HOUR,2);
        System.out.println(KEY);
        JwtBuilder builder = Jwts.builder()
                //唯一ID
                .setId(UUID.randomUUID().toString())
                //颁发人
                .setIssuer("bird")
                //颁发时间
                .setIssuedAt(new Date())
                //描述
                .setSubject("JWT令牌")
                //加密算法和密钥(盐)
                .signWith(SignatureAlgorithm.HS256,KEY)
                .setExpiration(TIME.getTime());
        return builder.compact();
    }

    /**
     * @Author lipu
     * @Date 2020/12/18 22:55
     * @Description 令牌解析
     */
    public static Map<String,Object> parseJwt(String jwt){
        //令牌解析
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt);
        JwsHeader header = claimsJws.getHeader();
        Claims body = claimsJws.getBody();
        Map<String,Object> info=new HashMap<>();
        info.put("header",header);
        info.put("body",body);
        return info;
    }


    public static void main(String[] args) {

    }

}
