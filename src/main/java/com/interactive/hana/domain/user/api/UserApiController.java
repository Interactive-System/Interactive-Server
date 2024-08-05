package com.interactive.hana.domain.user.api;

import com.interactive.hana.domain.accident.dto.AccidentResponse;
import com.interactive.hana.domain.contract.dto.ContractResponse;
import com.interactive.hana.domain.user.constant.UserConstants;
import com.interactive.hana.domain.user.domain.User;
import com.interactive.hana.domain.user.dto.SignUpUserRequest;
import com.interactive.hana.domain.user.dto.SignUpUserResponse;
import com.interactive.hana.domain.user.dto.UserInfoResponse;
import com.interactive.hana.domain.user.service.UserServiceImpl;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);

    private final UserServiceImpl userService;

    @PostMapping("signup")
    public ResponseEntity<SignUpUserResponse> signup(@Valid @RequestBody SignUpUserRequest dto) {
        User saved = userService.saveUser(dto);
        return ResponseEntity.ok(SignUpUserResponse.of(saved.getEmail(), UserConstants.SUCCESS_SIGN_UP.getMessage()));
    }

    @GetMapping("api/v1/user/my/contract")
    public ResponseEntity<List<ContractResponse>> findAllMyInsurance(@AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok(this.userService.findAllMyContract(principal));
    }

    @GetMapping("api/v1/user/info")
    public ResponseEntity<UserInfoResponse> userInfo(@AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok(this.userService.userInfo(principal));
    }

    @GetMapping("api/v1/user/accident/list")
    public ResponseEntity<List<AccidentResponse>> myAccidentList(@AuthenticationPrincipal PrincipalDetails principal) {
        return ResponseEntity.ok(this.userService.myAccidentList(principal));
    }

}
