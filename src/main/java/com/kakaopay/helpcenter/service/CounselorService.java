package com.kakaopay.helpcenter.service;


import com.kakaopay.helpcenter.entity.Counselor;
import com.kakaopay.helpcenter.dto.CounselorData;
import com.kakaopay.helpcenter.repository.CounselorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class CounselorService {

    @Autowired
    private CounselorRepository counselorRepository;

    /**
     * 상담사 아이디 조회.
     * @param id
     * @return
     */
    public CounselorData findByCounselorId(String id) {
        Counselor counselor = counselorRepository.findById(id);

        return CounselorData.builder()
                .id(counselor.getId())
                .userName(counselor.getUserName())
                .password(counselor.getPassword())
                .build();
    }

}
