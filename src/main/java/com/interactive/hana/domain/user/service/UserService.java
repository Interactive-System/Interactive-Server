package com.interactive.hana.domain.user.service;

import com.interactive.hana.domain.accident.dto.AccidentResponse;
import com.interactive.hana.domain.contract.dto.ContractResponse;
import com.interactive.hana.domain.user.domain.User;
import com.interactive.hana.domain.user.dto.SignUpUserRequest;
import com.interactive.hana.domain.user.dto.UserInfoResponse;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;

import java.util.List;

public interface UserService {

    User saveUser(SignUpUserRequest dto);

    User findByEmail(String email);

    List<ContractResponse> findAllMyContract(PrincipalDetails principal);

    UserInfoResponse userInfo(PrincipalDetails principal);

    List<AccidentResponse> myAccidentList(PrincipalDetails principal);

    User findById(Long id);
}
