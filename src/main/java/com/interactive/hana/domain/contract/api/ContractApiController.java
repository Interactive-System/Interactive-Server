package com.interactive.hana.domain.contract.api;

import com.interactive.hana.domain.contract.dto.CalculatePaymentResponse;
import com.interactive.hana.domain.contract.dto.UwStateCountResponse;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import com.interactive.hana.global.dto.DefaultResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface ContractApiController<CreateReq> {

    @PostMapping("apply")
    ResponseEntity<DefaultResponseDto> apply(@AuthenticationPrincipal PrincipalDetails principal, @Valid @RequestBody CreateReq dto);

    @PostMapping("calculate")
    ResponseEntity<CalculatePaymentResponse> calculatePayment(@AuthenticationPrincipal PrincipalDetails principal, @Valid @RequestBody CreateReq dto);

    @GetMapping("dashboard/state/count")
    ResponseEntity<UwStateCountResponse> getUwStateCount();
}
