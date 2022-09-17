package com.kakaopay.helpcenter.controller;

import com.kakaopay.helpcenter.dto.AnswerData;
import com.kakaopay.helpcenter.dto.CounselsData;
import com.kakaopay.helpcenter.dto.QuestionsData;
import com.kakaopay.helpcenter.service.CounselService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 고객용 화면
     * @return
     */
    @GetMapping("/customer/counsel/list")
    public ModelAndView customerCounselList(@AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("customer/counsel");


        modelAndView.addObject("counselList", counselService.findAllByCounselDataList());
        modelAndView.addObject("moveUrl", "/customer/question");
        modelAndView.addObject("isCounselorSession", !ObjectUtils.isEmpty(userDetails));
        return modelAndView;
    }

    /**
     * 상담사용 화면
     * @return
     */
    @GetMapping("/counselor/counsel/list")
    public ModelAndView counselorCounselList(@AuthenticationPrincipal UserDetails userDetails) {
        ModelAndView modelAndView = new ModelAndView("counselor/counsel");

        log.info("::::::::::::::::{}", userDetails);

        CounselsData dataLists = counselService.findCounselsData(userDetails.getUsername());

        modelAndView.addObject("unspecifiedCounselDataList", dataLists.getUnspecifiedCounselDataList());
        modelAndView.addObject("appointedCounselDataList", dataLists.getAppointedCounselDataList());
        modelAndView.addObject("ㅑ", !ObjectUtils.isEmpty(userDetails));

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
     * 상담사 답변 작성용 화면
     * @return
     */
    @GetMapping("/counselor/answer/{no}")
    public ModelAndView answer(@PathVariable Long no) {
        ModelAndView modelAndView = new ModelAndView("/counselor/answer");

        modelAndView.addObject("answer",new AnswerData(no));

        return modelAndView;
    }



    /**
     * 질문 작성
     * @return
     */
    @PostMapping("/customer/questions/write")
    public ModelAndView questionsWrite(QuestionsData questionsData) {

        counselService.writeQuestion(questionsData);
        return new ModelAndView(new RedirectView("/customer/counsel/list"));
    }

    /**
     * 답변 작성
     * @return
     */
    @PostMapping("/counselor/answer/write")
    public ModelAndView answerWrite(AnswerData answerData) {
        counselService.writeAnswer(answerData);

        return new ModelAndView(new RedirectView("/customer/counsel/list"));
    }

}
