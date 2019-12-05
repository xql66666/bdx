package constants;

/**
 * @author: 磊大大
 * @date: 2019/11/4 11:22
 */
public class RedisKey {

    //注册验证码
    public static final String REDIS_CHECKCODE = "checkcode_";
    //登录验证码
    public static final String REDIS_LOGINCODE = "logincode_";
    //token
    public static final String REDIS_TOKEN = "token_";
    //用户注册短信发送次数
    public static final String REDIS_REGISTER_MESSAGE = "register_message_";
    //用户登录短信发送次数
    public static final String REDIS_LOGIN_MESSAGE = "login_message_";


}
