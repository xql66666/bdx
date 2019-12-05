package auth;


import entity.po.AuthUser;

/**
 * @author: 磊大大
 * @date: 2019/10/14 16:55
 */
public class AuthUserContext {

    private static ThreadLocal<AuthUser> userThreadLocal = new ThreadLocal<>();

    /**
     * 获取当前用户信息
     */
    public static AuthUser getCurrentUser() {
        return userThreadLocal.get();
    }


    /**
     * 清除线程变量
     */
    public static void clear() {
        userThreadLocal.set(null);
    }

    /**
     * 设置当前用户信息
     */
    public static void setCurrentUser(AuthUser user) {
        userThreadLocal.set(user);
    }

}
