package auth;


import entity.po.AuthUserInfo;

/**
 * @author: 磊大大
 * @date: 2019/10/14 16:55
 */
public class AuthUserInfoContext {

    private static ThreadLocal<AuthUserInfo> userInfoThreadLocal = new ThreadLocal<>();

    /**
     * 获取当前用户信息
     */
    public static AuthUserInfo getCurrentUser() {
        return userInfoThreadLocal.get();
    }


    /**
     * 清除线程变量
     */
    public static void clear() {
        userInfoThreadLocal.set(null);
    }

    /**
     * 设置当前用户信息
     */
    public static void setCurrentUser(AuthUserInfo user) {
        userInfoThreadLocal.set(user);
    }
}
