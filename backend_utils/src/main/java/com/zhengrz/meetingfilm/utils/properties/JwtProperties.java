package com.zhengrz.meetingfilm.utils.properties;

import lombok.Data;

@Data
public class JwtProperties {

    private static JwtProperties jwtProperties = new JwtProperties();
    private JwtProperties(){}
    public static JwtProperties getJwtProperties(){
        return jwtProperties;
    }

    public static final String JWT_PREFIX = "jwt";

    private String header = "Authorization";

    private String secret = "defaultSecret";

    private Long expiration = 604800L; // 默认是七天

    private String authPath = "login";

    private String md5Key = "randomKey";

    public static String getJwtPrefix() {
        return JWT_PREFIX;
    }


}
