package com.kakaopay.helpcenter.api.user.domain.counselor.exeption;

public class CounselorPasswordFailException extends RuntimeException {
    public CounselorPasswordFailException(String message) {
        super(message);
    }
}
