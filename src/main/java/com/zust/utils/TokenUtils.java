package com.zust.utils;




import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenUtils {
    /**
     * 生成token
     *
     * @return
     */
    public static String createToken(String userId, String sign) {
        return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // TODO 2小时后token过期, 应该配置到常量类中
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
    }

}
