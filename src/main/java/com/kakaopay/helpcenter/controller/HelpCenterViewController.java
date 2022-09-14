package com.kakaopay.helpcenter.controller;

import com.kakaopay.helpcenter.model.QuestionsData;
import com.kakaopay.helpcenter.service.CounselService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 고객센터 화면 컨트롤러
 */
@Controller
@Slf4j
public class HelpCenterViewController {

    private CounselService counselService;

    @Autowired
    public HelpCenterViewController(CounselService counselService) {
        this.counselService = counselService;
    }

    @GetMapping("/login")
    public String hello() {
        return "login";
    }

    /**
     * 고객용 화면
     * @return
     */
    @GetMapping("/customer/counsel/list")
    public ModelAndView customerFaq() {
        ModelAndView modelAndView = new ModelAndView("customer/counsel");

        modelAndView.addObject("counselList", counselService.getCounselDataList());
        modelAndView.addObject("moveUrl", "/customer/question");
        return modelAndView;
    }

    /**
     * 상담사용 화면
     * @return
     */
    @GetMapping("/counselor/counsel/list")
    public ModelAndView counselorFaq() {
        ModelAndView modelAndView = new ModelAndView("counselor/counsel");

        modelAndView.addObject("counselList", counselService.findAllByCounselorIdIsNull());
        return modelAndView;
    }

    /**
     * 고객 상담 작성용 화면
     * @return
     */
    @GetMapping("/customer/questions")
    public ModelAndView questions() {
        ModelAndView modelAndView = new ModelAndView("customer/questions");

        modelAndView.addObject("questions",new QuestionsData());

        return modelAndView;
    }


    /**
     * 작성
     * @return
     */
    @PostMapping("/customer/questions/write")
    public ModelAndView questionsWrite(QuestionsData questionsData) {
        counselService.addCounsel(questionsData);
        return new ModelAndView(new RedirectView("/customer/counsel/list"));
    }
}
