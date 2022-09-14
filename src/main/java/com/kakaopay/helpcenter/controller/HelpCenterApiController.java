package com.kakaopay.helpcenter.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelpCenterApiController {


    @Autowired
    public HelpCenterApiController() {

    }

    @PatchMapping("/api/counselor/assignment")
    public synchronized int counselorAssignment() {
        return 0;
    }



}
