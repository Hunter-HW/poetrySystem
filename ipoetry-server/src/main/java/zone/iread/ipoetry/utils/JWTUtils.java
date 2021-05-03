package zone.iread.ipoetry.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {

    private static final String SING = "asgdfhsaukfhsakjc"; // 签名

    /**
     * 生成token header.payload.sing
     */
    public static String getToken(Map<String, String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);// 七天过期
        // 创建jwt builder
        JWTCreator.Builder builder = JWT.create();
        // payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime()) // 指定令牌过期时间
                .sign(Algorithm.HMAC256(SING)); // 签名
        return token;
    }

    /**
     * 验证token 合法性
     */
    public static DecodedJWT verify(String token){
        // 创建验证对象
        return JWT.require(Algorithm.HMAC256("asgdfhsaukfhsakjc")).build().verify(token);
    }
}
