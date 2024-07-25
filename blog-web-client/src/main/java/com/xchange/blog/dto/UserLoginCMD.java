package com.xchange.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 20:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginCMD {

    private String username;

    private String password;
}
