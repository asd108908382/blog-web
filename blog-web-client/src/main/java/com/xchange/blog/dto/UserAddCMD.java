package com.xchange.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 20:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddCMD {

    private String email;
    private String password;
    private String username;
}
