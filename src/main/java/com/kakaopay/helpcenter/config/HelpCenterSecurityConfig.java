package com.kakaopay.helpcenter.config;


import com.kakaopay.helpcenter.service.HelpCenterSecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Slf4j
public class HelpCenterSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new HelpCenterSecurityService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 유효한 인증인지 아닌지 확인하는 매니저 빈 객체 설정
     * @param http
     * @param bCryptPasswordEncoder
     * @param userDetailsService
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    /**
     * js, images 등 허용 설정
     * @return
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
    }

    /**
     * 시큐리티 필터 설정
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();

        // 접근 설정
        http.authorizeHttpRequests()
                .antMatchers("/","/customer/**","/customer/counsel/**","/api/customer/**","/h2/**","/h2-console/**","/login/**").permitAll() // 허용
                .anyRequest().authenticated()
                .and().csrf().disable()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/counselor/counsel/list", true)
                .failureUrl("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();

        // 세션 설정
        http.sessionManagement()
                .maximumSessions(1) // 최대 허용 세션 (-1일시 무한)
                .maxSessionsPreventsLogin(false) // true일시 허용 세션 초과시 로그인 차단, false 일시 기존 세션 만료
                .expiredUrl("/login");

        return http.build();
    }




    /**
     * 인증 정보 초기화
     * @return
     */
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }
}
