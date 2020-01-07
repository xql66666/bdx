package com.bdx.manager.interceptor;

import com.alibaba.fastjson.JSON;
import constants.IdentityEnum;
import constants.RedisKey;
import constants.ResultCodeBase;
import constants.TipConstBase;
import entity.ResponseEntity;
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
import java.io.PrintWriter;

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
            System.out.println("是OPTIONS请求");
            response.reset();
            // 允许跨域访问的域名：若有端口需写全（协议+域名+端口），若没有端口末尾不用加'/'
            response.setHeader("Access-Control-Allow-Origin", "*");
            // 允许前端带认证cookie：启用此项后，上面的域名不能为'*'，必须指定具体的域名，否则浏览器会提示
            //response.setHeader("Access-Control-Allow-Credentials", "true");
            // 提示OPTIONS预检时，后端需要设置的两个常用自定义头
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, Timestamp");
            response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
            response.setHeader("Vary", "Accept-Encoding,Origin");

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter pw = response.getWriter();
            ResponseEntity<Object> responseEntity = new ResponseEntity<>();
            responseEntity.setCode(ResultCodeBase.CODE_SUCCESS);
            responseEntity.setMsg(TipConstBase.OPTIONS_SUCCESS);
            pw.write(JSON.toJSONString(responseEntity));
            pw.flush();
            pw.close();
            return true;
        }

        System.out.println("经过Manage拦截器~");
        String header = request.getHeader("Authorization");
        System.out.println("aaaaaa=" + header);
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
                    System.out.println("id是啊啊啊啊啊啊啊啊" + id + "============" + tokenRedis);
                    if (!token.equals(tokenRedis) || "".equals(tokenRedis)) {
                        throw new TokenErrorException("请重新登录"); //让前端跳转登录界面
                    }

                    String roles = (String) claims.get("roles");

                    if (roles != null && roles.equals(IdentityEnum.MANAGE.getDesc())) {
                        request.setAttribute("claims", IdentityEnum.MANAGE.getDesc());
                        request.setAttribute("userId",id);
                        return true;
                    }
                    if (roles != null && roles.equals(IdentityEnum.ROOT.getDesc())) {
                        request.setAttribute("claims", IdentityEnum.ROOT.getDesc());
                        request.setAttribute("userId",id);
                        return true;
                    }
                }catch (Exception e) {
                    //throw new TokenErrorException("请重新登录");
                    return jwtInterceptorUtil.returnTokenEntity(response);
                }
            }
        }
        //拼装响应信息
//        response.reset();
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/json;charset=UTF-8");
//        PrintWriter pw = response.getWriter();
//        ResponseEntity<Object> responseEntity = new ResponseEntity<>();
//        responseEntity.setCode(ResultCodeBase.CODE_ERROR_USER_IDENTITY_ERROR);
//        responseEntity.setMsg(TipConstBase.OPERATION_IDENTITY_ERROR);
//        pw.write(JSON.toJSONString(responseEntity));
//        pw.flush();
//        pw.close();
//        return false;
        System.out.println("aaaaaaaaaaaaaaaaaaaaa");
         return jwtInterceptorUtil.returnTokenEntity(response);
    }
}
