package com.kakaopay.helpcenter.model;

import lombok.*;

import javax.persistence.*;


/**
 * 상담 데이터
 * : 클라이언트에 반환 데이터
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CounselData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String createId;

    private String counselorName;

    private String title;

    private String detail;

    private String answer;

    private String regTime;

    private String answerTime;
}
