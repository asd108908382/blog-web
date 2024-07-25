package com.xchange.blog.web;

import com.alibaba.cola.dto.SingleResponse;
import com.xchange.blog.api.UserServiceI;
import com.xchange.blog.db.repository.UserRepository;
import com.xchange.blog.dto.UserAddCMD;
import com.xchange.blog.dto.UserLoginCMD;
import com.xchange.blog.filter.UserContext;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 20:31
 */
@RestController
@RequestMapping("auth")
public class UserController {

    @Resource
    UserServiceI userServiceI;

    @Resource
    UserRepository userRepository;

    //返回token
    @PostMapping("login")
    public SingleResponse<?> login(@RequestBody UserLoginCMD cmd) {
        return SingleResponse.of(userServiceI.login(cmd));
    }

    @PostMapping("register")
    public SingleResponse<?> register(@RequestBody UserAddCMD cmd) {
        return SingleResponse.of(userServiceI.register(cmd));
    }

    @GetMapping("me")
    public SingleResponse<?> me() {
        return SingleResponse.of(UserContext.getUser());
    }
}
