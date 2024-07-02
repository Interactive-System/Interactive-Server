package com.interactive.hana.infra.mail.service;

import com.interactive.hana.domain.user.dao.UserRepository;
import com.interactive.hana.domain.user.domain.User;
import com.interactive.hana.domain.user.domain.UserStateType;
import com.interactive.hana.domain.user.exception.EmailNotFoundException;
import com.interactive.hana.domain.user.exception.UserExceptionMessage;
import com.interactive.hana.infra.mail.dao.EmailAuthCodeRepository;
import com.interactive.hana.infra.mail.domain.EmailAuthCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EmailAuthService {

    private final EmailAuthCodeRepository emailAuthCodeRepository;
    private final UserRepository userRepository;

    @Transactional
    public void emailValidate(String email, String authCode) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(UserExceptionMessage.EMAIL_NOT_FOUND_EXCEPTION_MESSAGE));
        if (user.getState().equals(UserStateType.NORMAL)) return;
        EmailAuthCode emailAuthCode = emailAuthCodeRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(UserExceptionMessage.EMAIL_NOT_FOUND_EXCEPTION_MESSAGE));

        emailAuthCode.validateCode(authCode);
        user.emailVerificationCompleted();
        emailAuthCodeRepository.delete(emailAuthCode);
    }

}
