package com.kakaopay.helpcenter.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 세션 관리용 클래스
 */
@Component
public class HelpCenterSessionManager {

    private static final String SESSION_COOKIE_NAME = "help_center_session";

    private Map<String,Object> sessionStore = new ConcurrentHashMap<>();


    /**
     * 세션 생성
     * @param response 결과.
     * @param value 세션에 넣을 값.
     */
    public void createSession(HttpServletResponse response, Object value) {

        // 중복 방지를 위한 세션 아이디
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        Cookie sessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(sessionCookie);
    }

    /**
     * 세션 조회
     * @param request
     * @return
     */
    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookie = this.findCookieByRequest(request, SESSION_COOKIE_NAME);

        if(ObjectUtils.isEmpty(sessionCookie)) {
            return null;
        }

        return sessionStore.get(sessionCookie.getValue());
    }

    public void expire(HttpServletRequest request) {
        Cookie sessionCookie = this.findCookieByRequest(request, SESSION_COOKIE_NAME);
        if(sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
        }
    }

    private Cookie findCookieByRequest(HttpServletRequest request, String cookieName){
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookieName.equals(cookie.getName()))
                .findAny()
                .orElse(null);
    }

}
