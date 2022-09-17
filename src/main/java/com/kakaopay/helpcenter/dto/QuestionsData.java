package com.kakaopay.helpcenter.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QuestionsData {
    private String createId; // 고객아이디
    private String questionsTitle; // 질문제목
    private String questionsDetail; // 질문상세내용
}
