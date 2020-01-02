package com.potoyang.learn.shirojwt.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * Created with Intellij IDEA.
 *
 * @author wangyb
 * @Date 2018/9/3 13:58
 * Modified By:
 * Description:
 */
public class JwtUtil {
    private static final long EXPIRE_TIME = 432000000;

    /**
     * JWT生成token时加密用的secret
     */
    private static final String JWT_SECRET = "3MZq0BYyGcXYoXjhS4QbAM+2YdlLCwKRr2gvVJOJ";

    /**
     * 校验token是否正确
     *
     * @param token
     * @param username //     * @param secret
     * @return
     */
    public static boolean verify(String token, String username, String provider_id, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .withClaim("provider_id", provider_id)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取token中的信息
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer getProviderId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("provider_id").asInt();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获得token的有效期
     *
     * @param token
     * @return
     */
    public static Date getExpiresDate(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 生成签名,5min后过期
     *
     * @param username //     * @param secret
     * @return
     */
    public static String sign(String username, String secret) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            return null;
        }
    }
}
