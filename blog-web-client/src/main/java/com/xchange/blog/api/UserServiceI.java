package com.xchange.blog.api;

import com.xchange.blog.dto.UserAddCMD;
import com.xchange.blog.dto.UserLoginCMD;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 20:45
 */
public interface UserServiceI {
    String login(UserLoginCMD cmd);

    String register(UserAddCMD cmd);
}
