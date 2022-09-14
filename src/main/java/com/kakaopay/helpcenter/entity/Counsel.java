package com.kakaopay.helpcenter.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Counsel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(name = "create_id")
    private String createId;

    @Column(name = "counselorId")
    private String counselorId;

    @Column(name = "questions_title")
    private String title;

    @Column(name = "questions_detail")
    private String detail;

    private String answer;

    @Column(name = "reg_date")
    private Date regTime;

    @Column(name = "answer_date")
    private Date answerTime;

    @Builder
    public Counsel(Long no, String createId, String counselorId, String title, String detail, String answer, Date regTime, Date answerTime) {
        this.no = no;
        this.createId = createId;
        this.counselorId = counselorId;
        this.title = title;
        this.detail = detail;
        this.answer = answer;
        this.regTime = regTime;
        this.answerTime = answerTime;
    }
}
