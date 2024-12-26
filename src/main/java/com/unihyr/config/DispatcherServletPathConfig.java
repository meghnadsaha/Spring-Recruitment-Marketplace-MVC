package com.unihyr.config;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DispatcherServletPathConfig {
    @Bean
    public DispatcherServletPath dispatcherServletPath() {
        return () -> "/";
    }
}
