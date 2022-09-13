package com.kakaopay.helpCenter.api.user.domain.counselor.service;

import com.kakaopay.helpCenter.api.user.domain.counselor.entity.Counselor;
import com.kakaopay.helpCenter.api.user.domain.counselor.exeption.CounselorAccountExistIdException;
import com.kakaopay.helpCenter.api.user.domain.counselor.exeption.CounselorPasswordFailException;
import com.kakaopay.helpCenter.api.user.domain.counselor.repository.CounselorRepository;
import com.kakaopay.helpCenter.api.user.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(value = false)
@Slf4j
class CounselorServiceTest {

    @Autowired
    private CounselorRepository counselorRepository;

    @Autowired
    private CounselorService counselorService;

    @DisplayName("저장 테스트")
    @Test
    void registerTest() {
        Counselor counselor = Counselor
                                .builder()
                                .id("aaaa")
                                .userName("aaa")
                                .password("aodsjoajdoadjasd")
                                .build();

        counselorRepository.save(counselor);

        Counselor resultCounselor = counselorRepository.findById(counselor.getId());

        Assert.assertEquals(resultCounselor.getId(), "aaaa");
        Assert.assertEquals(resultCounselor.getUserName(), "aaa");
        Assert.assertEquals(resultCounselor.getPassword(), "aodsjoajdoadjasd");

    }

    @DisplayName("로그인 정보 조회")
    @Test
    void loginSuccessTest() {
        String id = "hong";
        String password = "1234";

        Counselor resultCounselor = counselorRepository.findByIdAndPassword(id,password);

        Assert.assertEquals(resultCounselor.getId(), "hong");
    }


    @DisplayName("계정이 없는 경우")
    @Test
    void loginFail01Test() {
        String id = "hong1";
        String password = "1234";

        Counselor resultCounselor = counselorRepository.findByIdAndPassword(id,password);

        Assert.assertTrue(ObjectUtils.isEmpty(resultCounselor));

        resultCounselor = counselorRepository.findById(id);

        Assert.assertTrue(ObjectUtils.isEmpty(resultCounselor));
    }

    @DisplayName("비밀번호가 틀린 경우")
    @Test
    void loginFail02Test(){
        String id = "hong";
        String password = "12345";

        Counselor resultCounselor = counselorRepository.findByIdAndPassword(id,password);

        Assert.assertTrue(ObjectUtils.isEmpty(resultCounselor));

        resultCounselor = counselorRepository.findById(id);

        Assert.assertTrue(!ObjectUtils.isEmpty(resultCounselor));
    }

    @DisplayName("실 로직을 검증한다(로그인 성공)")
    @Test
    void loginLogicSuccessTest() {
        UserRequest userRequest = UserRequest.builder()
                                .id("hong")
                                .password("1234")
                                .build();

        Assert.assertTrue(!ObjectUtils.isEmpty(counselorService.login(userRequest)));
    }

    @DisplayName("실 로직을 검증한다(계정 미존재)")
    @Test
    void loginLogicFail01Test() {
        UserRequest userRequest = UserRequest.builder()
                .id("hong1")
                .password("1234")
                .build();


        Assert.assertThrows(CounselorAccountExistIdException.class, () -> counselorService.login(userRequest));
    }

    @DisplayName("실 로직을 검증한다(비밀번호 틀림)")
    @Test
    void loginLogicFail02Test() {
        UserRequest userRequest = UserRequest.builder()
                .id("hong")
                .password("1231")
                .build();

        Assert.assertThrows(CounselorPasswordFailException.class, () -> counselorService.login(userRequest));
    }
}