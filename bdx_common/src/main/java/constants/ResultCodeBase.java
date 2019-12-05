package constants;

/**
 * @author: 磊大大
 * @date: 2019/10/12 15:24
 * HTTP接口返回状态码
 */
public class ResultCodeBase {

    public static final int CODE_SUCCESS = 200; // 成功

    public static final int CODE_BAD_REQUEST = 400; //无效请求

    public static final int CODE_EXCEPTION = 500; // 异常

    public static final int CODE_ERROR_USER_NOT_LOGIN = 600; //未登陆

    public static final int CODE_ERROR_USER_TOKEN_ERROR = 700; //Token验证失败

    public static final int CODE_ERROR_USER_IDENTITY_ERROR = 701; //权限不足

    public static final int CODE_ERROR_USER_SIGN_ERROR = 702; //签名校验失败

    public static final int CODE_ERROR_CURRENT_LIMITING = 800; //访问过于频繁

    public static final int CODE_ERROR_SYSTEM_MAINTENANCE = 900; //停机维护

    public static final int CODE_DATA_IS_NULL= 501; //数据为空


}
