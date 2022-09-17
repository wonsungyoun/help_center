package com.kakaopay.helpcenter.controller;


import com.kakaopay.helpcenter.dto.AssignmentRequestData;
import com.kakaopay.helpcenter.dto.CounselData;
import com.kakaopay.helpcenter.dto.CounselsData;
import com.kakaopay.helpcenter.service.CounselService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class HelpCenterApiController {

    // 상담서비스
    private CounselService counselService;

    @Autowired
    public HelpCenterApiController(CounselService counselService) {
        this.counselService =counselService;
    }

    /**
     * 상담사 지정
     * @param assignmentRequestData 지정할 데이터
     * @param userDetails 세션
     * @return
     */
    @PatchMapping("/api/counselor/assignment")
    public CounselsData counselorAssignment(
            @RequestBody AssignmentRequestData assignmentRequestData
            , @AuthenticationPrincipal UserDetails userDetails
    ) {
        return counselService.assignToACounselor(assignmentRequestData.getNo(), userDetails.getUsername());
    }

    /**
     * 상담사용 상담 리스트 api
     * @param userDetails
     * @return
     */
    @GetMapping("/api/counselor/getCounselList")
    public CounselsData getCounselorCounselList(@AuthenticationPrincipal UserDetails userDetails) {
        return counselService.findCounselsData(userDetails.getUsername());
    }

    /**
     * 고객용 상담리스트 api
     * @return
     */
    @GetMapping("/api/customer/getCustomerCounselList")
    public List<CounselData> getCustomerCounselList() {
        return counselService.findAllByCounselDataList();
    }


}
