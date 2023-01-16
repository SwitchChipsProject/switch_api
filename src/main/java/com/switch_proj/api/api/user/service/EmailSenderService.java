package com.switch_proj.api.api.user.service;

import com.switch_proj.api.api.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;
    private static final String ADMIN_ADDRESS = "min_ina@naver.com";

    public void sendMail(UserEntity user){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setFrom(ADMIN_ADDRESS);
        simpleMailMessage.setSubject("스위치 이메일 인증 메일입니다.");
        simpleMailMessage.setText("다음 링크를 눌러 스위치 이메일 인증을 완료해주세요\n"+"\"http://localhost:8080/api/v1/user/certification/"+user.getCertificationCode());
        javaMailSender.send(simpleMailMessage);
    }
}
