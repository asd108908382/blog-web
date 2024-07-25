package com.xchange.blog.filter;

import com.xchange.blog.db.po.User;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 20:53
 */
public class UserContext {

    private static final ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static User getUser() {
        return userThreadLocal.get();
    }

    public static void setUser(User user) {
        userThreadLocal.set(user);
    }

    public static void clear() {
        userThreadLocal.remove();
    }
}
