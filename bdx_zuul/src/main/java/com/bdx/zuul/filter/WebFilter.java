package com.bdx.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.bdx.zuul.filter.util.SignUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import constants.ResultCodeBase;
import constants.TipConstBase;
import entity.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author: 磊大大
 * @date: 2019/10/23 13:56
 */
@Component
public class WebFilter extends ZuulFilter {

    @Autowired
    private SignUtils signUtils;

    @Override
    public String filterType() {
        //前置过滤器
        return "pre";
    }

    @Override
    public int filterOrder() {
        //优先级0  数字越大 优先级越低
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行改过滤器 此处为true 说明需要过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul过滤器执行了...");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (request.getMethod().equals("OPTIONS")){
            return false;
        }
        String timestamp = request.getHeader("Timestamp");
        //TODO 暂时只添加url时效性校验 防篡改签名以后再弄
        if(!signUtils.timeIsTrue(timestamp)) {
            //拦截
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(200);
            // 允许跨域访问的域名：若有端口需写全（协议+域名+端口），若没有端口末尾不用加'/'
            requestContext.getResponse().setHeader("Access-Control-Allow-Origin", "*");
            // 允许前端带认证cookie：启用此项后，上面的域名不能为'*'，必须指定具体的域名，否则浏览器会提示
            requestContext.getResponse().setHeader("Access-Control-Allow-Credentials", "true");
            // 提示OPTIONS预检时，后端需要设置的两个常用自定义头
            requestContext.getResponse().setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
            requestContext.getResponse().setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
            requestContext.getResponse().setHeader("Vary", "Accept-Encoding,Origin");
            requestContext.getResponse().setContentType("application/json;charset=UTF-8");
            requestContext.setResponseBody(JSON.toJSONString(new ResponseEntity(ResultCodeBase.CODE_ERROR_USER_SIGN_ERROR, TipConstBase.OPERATION_SIGN_ERROR)));
            System.out.println("失效了哦");

            return false;

        }
        return true;
    }
}
