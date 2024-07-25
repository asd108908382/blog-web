package com.xchange.blog.service;

import com.alibaba.cola.exception.BizException;
import com.xchange.blog.api.UserServiceI;
import com.xchange.blog.db.po.User;
import com.xchange.blog.db.repository.UserRepository;
import com.xchange.blog.dto.UserAddCMD;
import com.xchange.blog.dto.UserLoginCMD;
import com.xchange.blog.utils.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 20:45
 */
@Service
public class UserService implements UserServiceI {

    @Resource
    UserRepository userRepository;


    @Override
    public String login(UserLoginCMD cmd) {
        User user = userRepository.findByUsername(cmd.getUsername());
        if (user == null || !user.getPassword().equals(cmd.getPassword())) {
            throw new BizException("用户不存在或密码错误");
        }
        return TokenUtils.generateToken(user.getUserId());
    }

    @Override
    public String register(UserAddCMD cmd) {
        User user = userRepository.findByUsername(cmd.getUsername());
        if (user != null) {
            throw new BizException("用户已存在");
        }
        User newUser = new User();
        newUser.setUsername(cmd.getUsername());
        newUser.setPassword(cmd.getPassword());
        newUser.setEmail(cmd.getEmail());
        userRepository.save(newUser);
        return TokenUtils.generateToken(newUser.getUserId());
    }
}
