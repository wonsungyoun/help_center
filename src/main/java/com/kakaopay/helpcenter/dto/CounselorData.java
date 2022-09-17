package com.kakaopay.helpcenter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Id;

@Getter
@Builder
public class CounselorData {
    @Id
    private String id;
    private String userName;

    @JsonIgnore
    private String password;
}
