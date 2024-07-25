package com.xchange.blog.api;

import com.alibaba.cola.dto.PageResponse;
import com.xchange.blog.dto.PostDoAdd;
import com.xchange.blog.dto.data.PostDTO;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 21:03
 */
public interface PostServiceI {
    PostDTO doCmd(PostDoAdd cmd);

    PostDTO findById(Long id);

    void deleteById(Long id);

    PageResponse<PostDTO> pageByUId(Long uid, int page, int size, String sort);
}
