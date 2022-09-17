package com.kakaopay.helpcenter.service;

import com.kakaopay.helpcenter.common.constants.AppointStatus;
import com.kakaopay.helpcenter.dto.*;
import com.kakaopay.helpcenter.entity.Counsel;
import com.kakaopay.helpcenter.mapper.CounselMapper;
import com.kakaopay.helpcenter.repository.CounselRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CounselService {

    @Autowired
    private CounselorService counselorService;

    @Autowired
    private CounselRepository counselRepository;

    @Autowired
    private CounselMapper counselMapper;

    /**
     * 질문 작성 및 상담 등록
     * @param questionsData 질문 데이터
     * @return
     */
    public boolean writeQuestion(QuestionsData questionsData) {

        try {
            // 상담 entity객체 생성
            Counsel counsel = Counsel.builder()
                    .createId(questionsData.getCreateId())
                    .title(questionsData.getQuestionsTitle())
                    .detail(questionsData.getQuestionsDetail())
                    .regTime(new Date())
                    .build();

            counselRepository.save(counsel);

            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 답변 작성
     * @param answerData
     * @return
     */
    public boolean writeAnswer(AnswerData answerData) {

        try {
            // 수정할 상담 조회
            Counsel counsel = counselRepository.findAllByNo(answerData.getNo());

            // 답변 업데이트 방지
            if (StringUtils.isNotEmpty(counsel.getAnswer())) {
                return false;
            }

            // 답변 업데이트
            counsel.writeAnswer(answerData.getAnswer());
            counselRepository.save(counsel);

            return true;
        }catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    /**
     * 상담데이타 조회
     * @return
     */
    public List<CounselData> findAllByCounselDataList() {
        return counselMapper.getCounselDataList();
    }

    /**
     * 상담 목록 조회
     * (미지정된 리스트와 지정하였으나 답변은 없는 리스트)
     * @param counselorId 상담사 아이디
     * @return
     */
    public CounselsData findCounselsData(String counselorId) {
        try {
            // 원본 전체 데이터
            List<CounselData> rawCounselListData = counselMapper.getCounselDataList();

            List<CounselData> unspecifiedCounselListData = new ArrayList<>();

            for(CounselData counselData : rawCounselListData) {
                if(StringUtils.isEmpty(counselData.getCounselorId())) {
                    unspecifiedCounselListData.add(counselData);
                }
            }

            // 지정은 되었으나 답변이 없는 리스트
            List<CounselData> appointedCounselListData = new ArrayList<>();

            for(CounselData counselData : rawCounselListData) {
                if(StringUtils.isNotEmpty(counselData.getCounselorId())
                                            && counselorId.equals(counselData.getCounselorId())
                                            && StringUtils.isEmpty(counselData.getAnswer())) {
                    appointedCounselListData.add(counselData);
                }
            }

            return new CounselsData(unspecifiedCounselListData, appointedCounselListData);
        }catch (Exception e){
            log.error(e.getMessage());
            return new CounselsData();
        }
    }

    /**
     * 상담사 지정
     * @param no 상담 번호
     * @param counselorId 상담사 아이디
     * @return
     */
    public CounselsData assignToACounselor(Long no, String counselorId) {

        try {

            // 지정할 상담 조회
            Counsel counsel = counselRepository.findAllByNo(no);

            if(StringUtils.isNotEmpty(counsel.getCounselorId())) {
                return new CounselsData(AppointStatus.APPOINT_OVERLAP); // 이미 지정된 상담건
            }

            // 상담사 지정을 위한 상담사 업데이트
            counsel.appointCounselor(counselorId);
            counselRepository.save(counsel);

            CounselsData counselsData = this.findCounselsData(counselorId);
            counselsData.appointResult(AppointStatus.APPOINT_SUCCESS);

            return counselsData;
        }catch (Exception e){
            log.error(e.getMessage());
            return new CounselsData(AppointStatus.APPOINT_FAIL);
        }
    }

}
