package com.kakaopay.helpcenter.common.result;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseObject<T> {
    private String result;
    private String message;
    private T data;

    @Builder
    public ResponseObject(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }
}
