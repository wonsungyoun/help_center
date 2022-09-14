package com.kakaopay.helpcenter.service;

import com.kakaopay.helpcenter.entity.Counsel;
import com.kakaopay.helpcenter.mapper.CounselMapper;
import com.kakaopay.helpcenter.model.CounselData;
import com.kakaopay.helpcenter.model.QuestionsData;
import com.kakaopay.helpcenter.repository.CounselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounselService {

    @Autowired
    private CounselRepository counselRepository;

    @Autowired
    private CounselMapper counselMapper;

    public boolean addCounsel(QuestionsData questionsData) {

        try {
            Counsel counsel = Counsel.builder()
                    .createId(questionsData.getCreateId())
                    .title(questionsData.getQuestionsTitle())
                    .detail(questionsData.getQuestionsDetail())
                    .build();

            counselRepository.save(counsel);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Counsel> findAll() {
        return counselRepository.findAllBy();
    }

    public List<Counsel> findAllByCounselorIdIsNull() {
        return counselRepository.findAllByCounselorIdIsNull();
    }

    /**
     * 상담데이타 조회
     * @return
     */
    public List<CounselData> getCounselDataList() {
        return counselMapper.getCounselDataList();
    }


}
