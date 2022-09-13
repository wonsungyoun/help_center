package com.kakaopay.helpCenter.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

public class CommonWebMvcConfigure implements WebMvcConfigurer {


    private UserRequestResolver helpCenterResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(helpCenterResolver);
    }

}
