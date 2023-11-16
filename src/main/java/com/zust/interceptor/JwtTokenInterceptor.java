package com.zust.interceptor;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.zust.dao.UserMapper;
import com.zust.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Resource
    private UserMapper userMapper;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader("token");

        //2、校验令牌
        String userid;
        try {
            log.info("jwt校验:{}", token);
            userid = JWT.decode(token).getAudience().get(0);
        } catch (Exception ex) {
            response.setStatus(401);
            return false;
        }
        //根据token中的userid查询数据库
        User user = userMapper.selectById(Integer.valueOf(userid));
        if (user==null){
            response.setStatus(401);
            return false;
        }
        //通过用户密码加密后生成一个验证器
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        }catch (Exception ex){
            response.setStatus(401);
            return false;
        }
        //3、通过，放行
        return true;
    }
}
