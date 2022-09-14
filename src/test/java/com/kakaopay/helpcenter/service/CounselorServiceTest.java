package com.kakaopay.helpcenter.service;

import com.kakaopay.helpcenter.entity.Counselor;
import com.kakaopay.helpcenter.repository.CounselorRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ObjectUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(value = false)
@Slf4j
public class CounselorServiceTest {

    @Autowired
    private CounselorRepository counselorRepository;

    @Autowired
    private CounselorService counselorService;


    @DisplayName("로그인 정보 조회")
    @Test
    public void loginSuccessTest() {
        String id = "hong";
        String password = "1234";

        Counselor resultCounselor = counselorRepository.findByIdAndPassword(id,password);

        Assert.assertEquals(resultCounselor.getId(), "hong");
    }


    @DisplayName("계정이 없는 경우")
    @Test
    public void loginFail01Test() {
        String id = "hong1";
        String password = "1234";

        Counselor resultCounselor = counselorRepository.findByIdAndPassword(id,password);

        Assert.assertTrue(ObjectUtils.isEmpty(resultCounselor));

        resultCounselor = counselorRepository.findById(id);

        Assert.assertTrue(ObjectUtils.isEmpty(resultCounselor));
    }

    @DisplayName("비밀번호가 틀린 경우")
    @Test
    public void loginFail02Test(){
        String id = "hong";
        String password = "12345";

        Counselor resultCounselor = counselorRepository.findByIdAndPassword(id,password);

        Assert.assertTrue(ObjectUtils.isEmpty(resultCounselor));

        resultCounselor = counselorRepository.findById(id);

        Assert.assertTrue(!ObjectUtils.isEmpty(resultCounselor));
    }

}