package com.kakaopay.helpCenter.common.config;

import com.kakaopay.helpCenter.api.user.annotation.UserInfo;
import com.kakaopay.helpCenter.api.user.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * request 정보 자바 객체로 변환
 */

@Slf4j
public class UserRequestResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(UserInfo.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        log.info("::{}",request);

        String id  = request.getParameter("id");
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");

        UserRequest userRequest = UserRequest
                                    .builder()
                                    .id(id)
                                    .userName(userName)
                                    .password(password)
                                    .build();

        return userRequest;
    }
}
