package com.switch_proj.api.api.user.service;

import com.switch_proj.api.api.exception.dto.BadRequestException;
import com.switch_proj.api.api.exception.dto.ExceptionEnum;
import com.switch_proj.api.api.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    private final JavaMailSender javaMailSender;
    private static final String ADMIN_ADDRESS = "min_ina@naver.com";

    public SimpleMailMessage sendMail(UserEntity user) throws MailException {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(user.getEmail());
            simpleMailMessage.setFrom(ADMIN_ADDRESS);
            simpleMailMessage.setSubject("스위치 이메일 인증 메일입니다.");
            simpleMailMessage.setText("다음 링크를 눌러 스위치 이메일 인증을 완료해주세요\n" + "http://localhost:8080/api/v1/user/certification/" + user.getCertificationCode());
            javaMailSender.send(simpleMailMessage);
            return simpleMailMessage;
        } catch (MailException e) {
            throw new BadRequestException(ExceptionEnum.REQUEST_PARAMETER_INVALID, "메일 인증에 실패하였습니다. 확인 후 제대로 입력해주세요");
        }

    }


}
