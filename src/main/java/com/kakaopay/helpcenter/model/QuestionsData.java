package com.kakaopay.helpcenter.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QuestionsData {
    private String createId;
    private String questionsTitle;
    private String questionsDetail;
}
