package com.interactive.hana.domain.user.service;

import com.interactive.hana.domain.accident.dto.AccidentResponse;
import com.interactive.hana.domain.accident.service.AccidentService;
import com.interactive.hana.domain.contract.dto.ContractResponse;
import com.interactive.hana.domain.user.dao.UserRepository;
import com.interactive.hana.domain.user.domain.User;
import com.interactive.hana.domain.user.dto.SignUpUserRequest;
import com.interactive.hana.domain.user.dto.UserInfoResponse;
import com.interactive.hana.domain.user.exception.EmailDuplicateException;
import com.interactive.hana.domain.user.exception.EmailNotVerifiedException;
import com.interactive.hana.domain.user.exception.UserExceptionMessage;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import com.interactive.hana.infra.mail.dao.EmailAuthCodeRepository;
import com.interactive.hana.infra.mail.domain.EmailAuthCode;
import com.interactive.hana.infra.mail.domain.EmailSubject;
import com.interactive.hana.infra.mail.util.EmailAuthCodeGenerator;
import com.interactive.hana.infra.mail.util.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final EmailAuthCodeRepository emailAuthCodeRepository;
    private final UserRepository userRepository;
    private final EmailUtil emailUtil;
    private final PasswordEncoder passwordEncoder;
    private final AccidentService accidentService;

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(UserExceptionMessage.USERNAME_NOT_FOUND_EXCEPTION_MESSAGE.getMessage()));
    }

    @Override
    public User saveUser(SignUpUserRequest dto) {
        if (emailAuthCodeRepository.existsByEmail(dto.getEmail()))
            throw new EmailNotVerifiedException(UserExceptionMessage.EMAIL_NOT_VERIFIED_EXCEPTION_MESSAGE);
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new EmailDuplicateException(UserExceptionMessage.EMAIL_DUPLICATE_EXCEPTION_MESSAGE);

        EmailAuthCodeGenerator authCodeGenerator = new EmailAuthCodeGenerator();
        String authCode = authCodeGenerator.generateAuthCode();
        emailAuthCodeRepository.save(EmailAuthCode.builder()
                .email(dto.getEmail())
                .authCode(authCode)
                .build());

        String message = emailUtil.getEmailAuthMessage(dto.getEmail(), authCode);
        emailUtil.sendEmail(dto.getEmail(), EmailSubject.EMAIL_AUTH_REQUEST, message);

        return userRepository.save(dto.toEntity(passwordEncoder));
    }

    @Override
    public List<ContractResponse> findAllMyContract(PrincipalDetails principal) {
        return findByEmail(principal.getUsername()).getContractList().stream().map(ContractResponse::from).collect(Collectors.toList());
    }

    @Override
    public UserInfoResponse userInfo(PrincipalDetails principal) {
        return UserInfoResponse.from(findByEmail(principal.getUsername()));
    }

    @Override
    public List<AccidentResponse> myAccidentList(PrincipalDetails principal) {
        return this.accidentService.accidentListFindByUser(findByEmail(principal.getUsername()));
    }

}
