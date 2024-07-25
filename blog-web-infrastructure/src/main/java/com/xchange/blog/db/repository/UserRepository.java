package com.xchange.blog.db.repository;

import com.xchange.blog.db.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author guojiawei
 * @version 1.0
 * @date 2024/7/25 20:39
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByUserId(Long userId);
}
