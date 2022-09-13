package com.kakaopay.helpCenter.api.user.domain.counselor.service;


import com.kakaopay.helpCenter.api.user.application.UserService;
import com.kakaopay.helpCenter.api.user.domain.counselor.entity.Counselor;
import com.kakaopay.helpCenter.api.user.domain.counselor.exeption.CounselorPasswordFailException;
import com.kakaopay.helpCenter.api.user.domain.counselor.repository.CounselorRepository;
import com.kakaopay.helpCenter.api.user.model.UserRequest;
import com.kakaopay.helpCenter.api.user.domain.counselor.exeption.CounselorAccountExistIdException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CounselorService implements UserService {

    @Autowired
    private CounselorRepository counselorRepository;

    /**
     * 회원 가입
     * @param userRequest 요청 객체
     * @return
     */
    @Override
    public Counselor register(UserRequest userRequest) {

        try {

            // 중복 확인
            Counselor counselorCheckObj = counselorRepository.findById(userRequest.getId());

            // 존재하는 경우 예외를 던진다.
            if(!ObjectUtils.isEmpty(counselorCheckObj)) {
                throw new CounselorAccountExistIdException("이미 존재하는 계정입니다.");
            }

            // entity 객체 생성후 데이터베이스에 저장한다.
            Counselor counselor = Counselor
                    .builder()
                    .id(userRequest.getId())
                    .userName(userRequest.getUserName())
                    .password(userRequest.getPassword())
                    .build();

            counselorRepository.save(counselor);

            return counselor;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    /**
     * 로그인
     * @param userRequest
     * @return
     */
    @Override
    public Counselor login(UserRequest userRequest) {

        Counselor counselor = counselorRepository.findByIdAndPassword(userRequest.getId(), userRequest.getPassword());

        // 결과값이 안넘어오는 경우
        if(ObjectUtils.isEmpty(counselor)) {

            // 비밀번호가 틀렸는지 확인한다.
            counselor = counselorRepository.findById(userRequest.getId());

            // 결과값이 조회가 되지않은 경우 계정이 아예없는 경우고 있는 경우 비밀번호가 틀린 경우이다.
            if(ObjectUtils.isEmpty(counselor)) {
                throw new CounselorAccountExistIdException("계정이 없습니다.");
            }else {
                throw new CounselorPasswordFailException("비밀번호가 틀렸습니다.");
            }

        }

        return counselor;
    }

    @Override
    public List<Counselor> findListAll() {

        try{
            List<Counselor> list = counselorRepository.findAllBy();

            return list;
        }catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }
}
