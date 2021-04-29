package com.dsmpear.main.user_backend_v2.service.email;

import com.dsmpear.main.user_backend_v2.entity.verifynumber.VerifyNumber;
import com.dsmpear.main.user_backend_v2.entity.verifynumber.VerifyNumberRepository;
import com.dsmpear.main.user_backend_v2.exception.EmailSendFailException;
import com.dsmpear.main.user_backend_v2.exception.SecretKeyMismatchException;
import com.dsmpear.main.user_backend_v2.payload.request.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;
    private final VerifyNumberRepository verifyNumberRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server.url}")
    private String url;

    @Value("${secret.key}")
    private String secretKey;

    @Override
    public void sendNotification(NotificationRequest request, String secretKey) {

        if (!passwordEncoder.matches(secretKey, this.secretKey)) throw new SecretKeyMismatchException();

        try {
            final MimeMessagePreparator preparator = mimeMessage -> {
                final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setFrom("pearavocat@gmail.com");
                helper.setTo(request.getEmail());
                helper.setSubject("PEAR 알림");
                helper.setText(convertNotificationHtmlWithString(request), true);
            };

            javaMailSender.send(preparator);
        } catch (Exception e) {
            logger.error("Notification Mail Send Error" + e.getMessage());
            throw new EmailSendFailException();
        }
    }

    @Override
    public void sendAuthNumEmail(String sendTo) {
        String authNum = generateVerifyNumber();

        try {
            final MimeMessagePreparator preparator = mimeMessage -> {
                final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                helper.setFrom("pearavocat@gmail.com");
                helper.setTo(sendTo);
                helper.setSubject("PEAR 회원가입 인증번호 안내 메일입니다.");
                helper.setText(convertNumberHtmlWithString(authNum), true);
            };

            javaMailSender.send(preparator);

            verifyNumberRepository.save(
                    VerifyNumber.builder()
                            .email(sendTo)
                            .verifyNumber(authNum)
                            .build()
            );
        } catch (Exception e) {
            logger.error("Auth Mail Send Failed" + e.getMessage() + "\nsendTo : " + sendTo);
            throw new EmailSendFailException();
        }
    }

    private String generateVerifyNumber() {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        return Integer.toString(random.nextInt(1000000) % 1000000);
    }

    @SneakyThrows
    private String convertNumberHtmlWithString(String code) {
        InputStream inputStream = new ClassPathResource("static/number_email.html").getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder stringBuilder = new StringBuilder();

        bufferedReader.lines()
                .filter(Objects::nonNull)
                .forEach(stringBuilder::append);

        return stringBuilder.toString().replace("{%code%}", code);
    }

    @SneakyThrows
    private String convertNotificationHtmlWithString(NotificationRequest request) {
        InputStream inputStream = new ClassPathResource("static/notification_email.html").getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder stringBuilder = new StringBuilder();

        bufferedReader.lines()
                .filter(Objects::nonNull)
                .forEach(stringBuilder::append);

        String body = stringBuilder.toString();

        if (request.getIsAccepted())
            body = body.replace("{{accepted}}", "승인");
        else {
            body = body.replace("{{accepted}}", "거부");
        }

        return body.replace("{{board_url}}", url + "report/" + request.getBoardId()).replace("{{body}}", request.getBody());
    }

}
