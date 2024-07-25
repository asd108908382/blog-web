package com.xchange.blog.config;

import com.xchange.blog.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(authFilterBean());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public AuthFilter authFilterBean() {
        return new AuthFilter();
    }
}
