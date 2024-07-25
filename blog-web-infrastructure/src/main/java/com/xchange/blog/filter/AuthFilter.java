package com.xchange.blog.filter;

import com.xchange.blog.db.po.User;
import com.xchange.blog.db.repository.UserRepository;
import com.xchange.blog.utils.TokenUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

/**
 * @author zyb
 * @date 2024-05-18 14:08
 */
@Slf4j
public class AuthFilter extends OncePerRequestFilter {

    @Resource
    UserRepository userRepository;

    private static final Set<String> IGNORE_URLS = Set.of("/api/auth/login", "/api/auth/register");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String path = request.getServletPath();
        if (IGNORE_URLS.stream().anyMatch(path::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (TokenUtils.isExpire(request.getHeader("token"))) {
            response.setStatus(401);
            return;
        }
        try {
            Long userId = TokenUtils.getId(request.getHeader("token"));
            User user = userRepository.findByUserId(userId);
            UserContext.setUser(user);
            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }
    }


}
