package com.kakaopay.helpCenter.common.result;

import com.kakaopay.helpCenter.common.constants.RequestResult;
import com.kakaopay.helpCenter.common.constants.ResponseMessage;
import org.springframework.stereotype.Service;

@Service
public class ResponseObjectFactory<T> {

    /**
     * 성공 시 넘겨줄 응답 객체
     * @param data 요청에 따른 응답 값
     * @return
     */
    public ResponseObject getSuccessObject(T data, String message) {
        return ResponseObject.builder()
                .result(RequestResult.OK)
                .message(message)
                .data(data)
                .build();
    }

    /**
     * 실패 시 넘겨줄 응답 객체
     * @return
     */
    public ResponseObject getFailObject(String message) {
        return ResponseObject.builder()
                .result(RequestResult.FAIL)
                .message(message)
                .build();
    }
}
