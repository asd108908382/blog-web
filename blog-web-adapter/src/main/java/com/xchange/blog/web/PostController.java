package com.xchange.blog.web;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.xchange.blog.api.PostServiceI;
import com.xchange.blog.dto.PostDoAdd;
import com.xchange.blog.dto.data.PostDTO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 21:03
 */
@RestController
@RequestMapping("posts")
public class PostController {

    @Resource
    PostServiceI postServiceI;

    @PostMapping
    public SingleResponse<PostDTO> add(@RequestBody PostDoAdd cmd) {
        return SingleResponse.of(postServiceI.doCmd(cmd));
    }

    @GetMapping("{id}")
    public SingleResponse<PostDTO> get(@PathVariable("id") Long id) {
        return SingleResponse.of(postServiceI.findById(id));
    }

    @PutMapping("{id}")
    public SingleResponse<PostDTO> update(@PathVariable("id") Long id, @RequestBody PostDoAdd cmd) {
        cmd.setPostId(id);
        return SingleResponse.of(postServiceI.doCmd(cmd));
    }

    @DeleteMapping("{id}")
    public SingleResponse<?> delete(@PathVariable("id") Long id) {
        postServiceI.deleteById(id);
        return SingleResponse.buildSuccess();
    }

    @GetMapping
    public PageResponse<PostDTO> list(@RequestParam(value = "uid") Long uid, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "sort", defaultValue = "ASC") String sort) {
        return postServiceI.pageByUId(uid, page, size,sort);
    }

}
