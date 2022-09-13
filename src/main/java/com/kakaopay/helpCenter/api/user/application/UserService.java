package com.kakaopay.helpCenter.api.user.application;

import com.kakaopay.helpCenter.api.user.domain.counselor.entity.Counselor;
import com.kakaopay.helpCenter.api.user.model.UserRequest;

import java.util.List;

/**
 * 유저 서비스 관련 인터페이스
 */
public interface UserService<T> {

    T register(UserRequest userRequest);

    Counselor login(UserRequest userRequest);

    List<T> findListAll();


}
