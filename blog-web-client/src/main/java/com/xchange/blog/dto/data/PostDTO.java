package com.xchange.blog.dto.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 21:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long postId;

    private String title;

    private String content;

    private Long userId;

    private LocalDateTime created;

    private LocalDateTime lastModified;
}
