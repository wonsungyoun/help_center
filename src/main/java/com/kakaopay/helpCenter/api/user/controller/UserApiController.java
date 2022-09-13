package com.kakaopay.helpCenter.api.user.controller;

import com.kakaopay.helpCenter.api.user.domain.counselor.entity.Counselor;
import com.kakaopay.helpCenter.api.user.application.UserService;
import com.kakaopay.helpCenter.api.user.domain.counselor.exeption.CounselorPasswordFailException;
import com.kakaopay.helpCenter.api.user.model.UserRequest;
import com.kakaopay.helpCenter.common.config.HelpCenterSessionManager;
import com.kakaopay.helpCenter.common.constants.ResponseMessage;
import com.kakaopay.helpCenter.api.user.domain.counselor.exeption.CounselorAccountExistIdException;
import com.kakaopay.helpCenter.common.result.ResponseObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * 유저 관련 API
 * : 상담사, 고객관련 API 컨트롤러
 * : 카카오페이 요구사항에 따라 상담사만 구현
 */
@RestController
public class UserApiController {

    private UserService userService;

    private HelpCenterSessionManager helpCenterSessionManager;

    private ResponseObjectFactory responseObjectFactory;

    @Autowired
    public UserApiController(UserService userService, HelpCenterSessionManager helpCenterSessionManager
                , ResponseObjectFactory responseObjectFactory) {
        this.userService = userService;
        this.helpCenterSessionManager = helpCenterSessionManager;
        this.responseObjectFactory = responseObjectFactory;
    }

    @GetMapping("/api/test")
    public String test() {
        return "쯔아아아악";
    }

    /**
     * 회원가입
     * @param userRequest 요청 파라미터
     * @return
     */
    @PostMapping("/api/counselor/register")
    public ResponseEntity register(@RequestBody UserRequest userRequest) {
        try {
            Counselor counselor = (Counselor) userService.register(userRequest);

            return new ResponseEntity(responseObjectFactory.getSuccessObject(counselor, ResponseMessage.REGISTER_SUCCESS)
                                      , HttpStatus.OK);

        }catch (CounselorAccountExistIdException ce) {
            return new ResponseEntity(responseObjectFactory.getFailObject(ResponseMessage.EXIST_ID)
                                      , HttpStatus.BAD_REQUEST);
        }catch (Exception e) {

            return new ResponseEntity(responseObjectFactory.getFailObject(ResponseMessage.SERVER_EXCEPTION)
                                      , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/counselor/list/all")
    public List<Counselor> findCounselorList() {
        return userService.findListAll();
    }

    /**
     * 로그인
     * @param userRequest 요청 정보
     * @param bindingResult 밸리데이션 결과
     * @param response
     * @return
     */
    @PostMapping("/api/counselor/login")
    public ResponseEntity login(@Valid @RequestBody UserRequest userRequest, BindingResult bindingResult, HttpServletResponse response) {

        try {
            if (bindingResult.hasErrors()) {
                return new ResponseEntity(responseObjectFactory.getFailObject(ResponseMessage.LOGIN_FAIL_01)
                        , HttpStatus.BAD_REQUEST);
            }

            Counselor counselor = userService.login(userRequest);

            helpCenterSessionManager.createSession(response, counselor);

            return new ResponseEntity(responseObjectFactory.getSuccessObject(counselor, ResponseMessage.LOGIN_SUCCESS), HttpStatus.OK);
        }catch (CounselorAccountExistIdException cae) {
            return new ResponseEntity(responseObjectFactory.getFailObject(ResponseMessage.LOGIN_FAIL_02), HttpStatus.BAD_REQUEST);
        }catch (CounselorPasswordFailException cpe) {
            return new ResponseEntity(responseObjectFactory.getFailObject(ResponseMessage.LOGIN_FAIL_03), HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            return new ResponseEntity(responseObjectFactory.getFailObject(ResponseMessage.SERVER_EXCEPTION)
                    , HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * 로그아웃
     * @param request 요청 객체
     * @return
     */
    @PostMapping("/api/counselor/logout")
    public String logout(HttpServletRequest request) {
        helpCenterSessionManager.expire(request);
        return "success";
    }
}
