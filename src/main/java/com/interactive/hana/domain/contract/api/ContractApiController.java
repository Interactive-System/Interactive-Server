package com.interactive.hana.domain.contract.api;

import com.interactive.hana.domain.contract.dto.*;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import com.interactive.hana.global.dto.DefaultResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface ContractApiController<CreateReq> {

    @PostMapping("apply")
    ResponseEntity<DefaultResponseDto> apply(@AuthenticationPrincipal PrincipalDetails principal, @Valid @RequestBody CreateReq dto);

    @PostMapping("calculate")
    ResponseEntity<CalculatePaymentResponse> calculatePayment(@AuthenticationPrincipal PrincipalDetails principal, @Valid @RequestBody CreateReq dto);

    @GetMapping("dashboard/state/count")
    ResponseEntity<UwStateCountResponse> getUwStateCount();

    @GetMapping("dashboard/quarter/count")
    ResponseEntity<ContractPerQuarterResponse> getContractPerQuarter();

    @GetMapping("dashboard/top/username")
    ResponseEntity<TopUsernameResponse> getTopUsername();

    @GetMapping("dashboard/top/insurance")
    ResponseEntity<List<TopInsuranceResponse>> getTopInsurance();

    @GetMapping("dashboard/contract/rate")
    ResponseEntity<ContractRateResponse> getContractRate();
}
