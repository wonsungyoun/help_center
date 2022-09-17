package com.kakaopay.helpcenter.service;

import com.kakaopay.helpcenter.dto.CounselorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class HelpCenterSecurityService implements UserDetailsService {

    @Autowired
    private CounselorService counselorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 로그인 정보 반환
     * @param counselorId the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String counselorId) throws UsernameNotFoundException {

        CounselorData counselor = counselorService.findByCounselorId(counselorId);

        return User.builder()
                .username(counselor.getId())
                .password(passwordEncoder.encode(counselor.getPassword()))
                .roles("USER")
                .build();
    }
}
