package com.kakaopay.helpcenter.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AnswerData {
    private Long no; // 상담 번호
    private String answer; // 답변

    public AnswerData() {

    }
    public AnswerData(Long no) {
        this.no = no;
    }

    public AnswerData(Long no, String answer) {
        this.no = no;
        this.answer = answer;
    }
}
