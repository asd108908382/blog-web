package com.xchange.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 21:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDoAdd {

    private Long postId;

    private String title;

    private String content;
}
