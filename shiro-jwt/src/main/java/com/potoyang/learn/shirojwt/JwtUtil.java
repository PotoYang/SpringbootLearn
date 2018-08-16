package com.potoyang.learn.shirojwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/15 11:38
 * Modified By:
 * Description:
 */
public class JwtUtil {
    private static final long EXPIRE_TIME = 5 * 60 * 1000;
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
    public static boolean verify(String token, String username, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
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
