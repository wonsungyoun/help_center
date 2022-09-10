package com.kakaopay.helpCenter.api.counselor.controller;

import com.kakaopay.helpCenter.api.counselor.model.Counselor;
import com.kakaopay.helpCenter.api.counselor.service.CounselorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounselorApiController {

    private CounselorService counselorService;

    @Autowired
    public CounselorApiController(CounselorService counselorService) {
        this.counselorService = counselorService;
    }

    @PostMapping("/counselor/login")
    public Counselor findCounselor() {

        return null;
    }
}
