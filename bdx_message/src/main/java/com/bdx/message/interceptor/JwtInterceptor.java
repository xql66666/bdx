package com.bdx.message.interceptor;

import constants.IdentityEnum;
import constants.RedisKey;
import exception.core.TokenErrorException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtInterceptorUtil;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 磊大大
 * @date: 2019/10/14 16:19
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtInterceptorUtil jwtInterceptorUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ("OPTIONS".equals(request.getMethod())) {
           // System.out.println("是options请求");
            return true;
        }

        //无论如何都放行 具体能不能操作还是在具体的从操作中去判断.
        //拦截器只是负责把头请求头中包含的token令牌进行严格解析验证
        String header = request.getHeader("Authorization");
        System.out.println("token头" + header);
        if (header != null && !"".equals(header)) {
            //如果有包含Authorization头信息 就对其进行解析
            if (header.startsWith("Bearer ")) {
                //得到token
                String token = header.substring(7);
                //对令牌进行验证
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String id = (String) claims.get("jti");
                    String tokenRedis = (String) redisTemplate.opsForValue().get(RedisKey.REDIS_TOKEN + id);
                    if (!token.equals(tokenRedis) || "".equals(tokenRedis)) {
                        throw new TokenErrorException("请重新登录");
                    }
                    String roles = (String) claims.get("roles");
                    if (roles != null && roles.equals(IdentityEnum.ORDINARY.getDesc())) {
                        request.setAttribute("claims", IdentityEnum.ORDINARY.getDesc());
                        request.setAttribute("userId",id);
                    }
                    if (roles != null && roles.equals(IdentityEnum.MEMBER.getDesc())) {
                        request.setAttribute("claims", IdentityEnum.MEMBER.getDesc());
                        request.setAttribute("userId",id);
                    }
                    if (roles != null && roles.equals(IdentityEnum.MANAGE.getDesc())) {
                        request.setAttribute("claims", IdentityEnum.MANAGE.getDesc());
                        request.setAttribute("userId",id);
                    }
                    if (roles != null && roles.equals(IdentityEnum.ROOT.getDesc())) {
                        request.setAttribute("claims", IdentityEnum.ROOT.getDesc());
                        request.setAttribute("userId",id);
                    }
                }catch (Exception e) {
                    return jwtInterceptorUtil.returnTokenEntity(response);
                }
            }
        }
        return true;
    }
}
