package com.kakaopay.helpCenter.api.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Getter
@NoArgsConstructor
public class UserRequest {
    @NotEmpty(message = "아이디는 필수 입니다.")
    private String id;

    @JsonProperty("user_name")
    private String userName;

    @NotEmpty(message = "패스워드는 필수 입니다.")
    private String password;


    @Builder
    public UserRequest(String id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    @Builder
    public UserRequest(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
