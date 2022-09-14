package com.kakaopay.helpcenter.service;


import com.kakaopay.helpcenter.entity.Counselor;
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
     * 회원 가입
     *
     * @param id 상담사 아이디
     * @return
     */
    public Counselor findByCounselorId(String id) {
        return counselorRepository.findById(id);
    }

}
