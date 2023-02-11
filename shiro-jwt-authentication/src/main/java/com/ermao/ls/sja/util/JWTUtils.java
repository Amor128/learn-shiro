package com.ermao.ls.sja.util;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Base64;

/**
 * @author Ermao
 * Date: 2023/2/11 19:02
 */
public class JWTUtils {
    //创建默认的秘钥和算法，供无参的构造方法使用
    private static final String SECRET = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
    private static final SignatureAlgorithm DEFAULT_SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;


    /**
     * 根据指定信息生成 JWT，参考
     * <a href="https://www.viralpatel.net/java-create-validate-jwt-token/">
     * Create and Validate JWT Token in Java using JJWT</a>。
     * <p>
     * 暂时使用 hardcode 的 key 和 algorithm。
     *
     * @param iss       issue 签发人
     * @param ttlMillis ttl in millisecond
     * @param claims    payload
     * @return 经过签名后的完整的 JWT
     */
    public static String encode(String iss, long ttlMillis, Map<String, Object> claims) {
        //iss签发人，ttlMillis生存时间，claims是指还想要在jwt中存储的一些非隐私信息
        if (claims == null) {
            claims = new HashMap<>();
        }
        Instant now = Instant.now();
        SecretKeySpec hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET),
                SignatureAlgorithm.HS256.getJcaName());
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .setSubject(iss)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .signWith(hmacKey);

        if (ttlMillis >= 0) {
            Instant ddl = now.plus(ttlMillis, ChronoUnit.MILLIS);
            jwtBuilder.setExpiration(Date.from(ddl));
        }
        return jwtBuilder.compact();
    }

    public static Jws<Claims> decode(String jwtToken) {
        SecretKeySpec hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET),
                SignatureAlgorithm.HS256.getJcaName());
        return Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(jwtToken);
    }
}
