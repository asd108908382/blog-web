package com.xchange.blog.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import com.xchange.blog.api.PostServiceI;
import com.xchange.blog.db.po.Post;
import com.xchange.blog.db.repository.PostRepository;
import com.xchange.blog.dto.PostDoAdd;
import com.xchange.blog.dto.data.PostDTO;
import com.xchange.blog.filter.UserContext;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 21:11
 */
@Service
public class PostService implements PostServiceI {

    @Resource
    PostRepository postRepository;

    @Override
    public PostDTO doCmd(PostDoAdd cmd) {
        Assert.notNull(cmd, "cmd is null");
        Post post = new Post();
        if (cmd.getPostId() == null) {
            post.setContent(cmd.getContent());
            post.setTitle(cmd.getTitle());
            post.setUserId(UserContext.getUser().getUserId());
            postRepository.save(post);
        } else {
            post = postRepository.findByPostId(cmd.getPostId());
            Assert.notNull(post, "post is null");
            if (!post.getUserId().equals(UserContext.getUser().getUserId())) {
                throw new BizException("not your post");
            }
            post.setContent(cmd.getContent());
            post.setTitle(cmd.getTitle());
            postRepository.save(post);
        }
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        return postDTO;
    }

    @Override
    public PostDTO findById(Long id) {
        Post post = postRepository.findByPostId(id);
        Assert.notNull(post, "post is null");
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        return postDTO;
    }

    @Override
    public void deleteById(Long id) {
        Post post = postRepository.findByPostId(id);
        if (post != null) {
            if (post.getUserId().equals(UserContext.getUser().getUserId())) {
                postRepository.delete(post);
            } else {
                throw new BizException("not your post");
            }
        }
    }

    @Override
    public PageResponse<PostDTO> pageByUId(Long uid, int page, int size, String sort) {
        sort = sort.equalsIgnoreCase("ASC") ? "ASC" : "DESC";
        Sort pageSort;
        if (sort.equalsIgnoreCase("ASC")) {
            pageSort = Sort.by(Sort.Direction.ASC, "created");
        } else {
            pageSort = Sort.by(Sort.Direction.DESC, "created");
        }
        PageRequest pageRequest = PageRequest.of(page, size, pageSort);
        Page<Post> all = postRepository.findAll(pageRequest);
        if (CollectionUtils.isEmpty(all.getContent())) {
            return PageResponse.buildSuccess();
        }
        List<PostDTO> postList = new ArrayList<>();
        all.getContent().forEach(post -> {
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postList.add(postDTO);
        });
        return PageResponse.of(postList, (int) all.getTotalElements(), all.getSize(), all.getNumber());
    }
}
