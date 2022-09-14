package com.kakaopay.helpcenter.service;

import com.kakaopay.helpcenter.entity.Counsel;
import com.kakaopay.helpcenter.mapper.CounselMapper;
import com.kakaopay.helpcenter.model.CounselData;
import com.kakaopay.helpcenter.repository.CounselRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(value = false)
@Slf4j
public class CounselServiceTest {

    @Autowired
    private CounselRepository counselRepository;

    @Autowired
    private CounselMapper counselMapper;

    @Autowired
    private CounselService counselService;



    @DisplayName("레파지토리 조회 테스트")
    @Test
    public void findAllByCounselorIdIsNull() {
        List<Counsel> faqList = counselRepository.findAllByCounselorIdIsNull();

        Assert.assertTrue(faqList.size()>0); // 정상적으로 조회가 되어야 함.
    }

    @DisplayName("조회 로직 테스트")
    @Test
    public void findAllByCounselorIdIsNullRealLogic() {
        List<Counsel> faqList = counselService.findAllByCounselorIdIsNull();

        Assert.assertTrue(faqList.size()>0); // 정상적으로 조회가 되어야 함.
    }

    @Test
    public void getCounselDataList() {
        List<CounselData>  counselDataList = counselMapper.getCounselDataList();

        log.info("::::::::::{}", counselDataList);

        Assert.assertTrue(counselDataList.size() > 0);
    }
}