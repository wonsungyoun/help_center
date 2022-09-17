package com.kakaopay.helpcenter.dto;

import com.kakaopay.helpcenter.common.constants.AppointStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


/**
 * 상담 데이터
 * : 클라이언트에 반환 데이터
 */
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CounselData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String createId;

    private String counselorId;

    private String counselorName;

    private String title;

    private String detail;

    private String answer;

    private String regTime;

    private String answerTime;
}
