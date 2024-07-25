package com.xchange.blog.db.repository;

import com.xchange.blog.db.po.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 21:01
 */
@org.springframework.stereotype.Repository
public interface PostRepository extends JpaRepository<com.xchange.blog.db.po.Post, Long>, PagingAndSortingRepository<com.xchange.blog.db.po.Post, Long> {
    Post findByPostId(Long postId);
}
