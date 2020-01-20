package util;

import com.alibaba.fastjson.JSON;
import constants.ResultCodeBase;
import constants.TipConstBase;
import entity.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: 磊大大
 * @date: 2019/11/29 14:43
 */

public class JwtInterceptorUtil {

    public boolean returnTokenEntity(HttpServletResponse response) throws IOException {
        response.reset();
        // 允许跨域访问的域名：若有端口需写全（协议+域名+端口），若没有端口末尾不用加'/'
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许前端带认证cookie：启用此项后，上面的域名不能为'*'，必须指定具体的域名，否则浏览器会提示
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 提示OPTIONS预检时，后端需要设置的两个常用自定义头
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, Timestamp");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
        response.setHeader("Vary", "Accept-Encoding,Origin");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        ResponseEntity<Object> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(ResultCodeBase.CODE_ERROR_USER_TOKEN_ERROR);
        responseEntity.setMsg(TipConstBase.OPERATION_TOKEN_TIME);
        pw.write(JSON.toJSONString(responseEntity));
        pw.flush();
        pw.close();
        return false;
    }

    public boolean returnUnLoginTokenEntity(HttpServletResponse response) throws IOException {
        response.reset();
        // 允许跨域访问的域名：若有端口需写全（协议+域名+端口），若没有端口末尾不用加'/'
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许前端带认证cookie：启用此项后，上面的域名不能为'*'，必须指定具体的域名，否则浏览器会提示
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 提示OPTIONS预检时，后端需要设置的两个常用自定义头
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, Timestamp");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
        response.setHeader("Vary", "Accept-Encoding,Origin");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = response.getWriter();
        ResponseEntity<Object> responseEntity = new ResponseEntity<>();
        responseEntity.setCode(ResultCodeBase.CODE_ERROR_USER_NOT_LOGIN);
        responseEntity.setMsg(TipConstBase.OPERATION_NO_LOGIN);
        pw.write(JSON.toJSONString(responseEntity));
        pw.flush();
        pw.close();
        return false;
    }
}
