package com.kakaopay.helpcenter.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Counselor {

    @Id
    private String id;
    private String userName;

    @JsonIgnore
    private String password;

    @Builder
    public Counselor(String id, String userName, String password) {
        this.id= id;
        this.userName = userName;
        this.password = password;
    }

    @Builder
    public Counselor(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
