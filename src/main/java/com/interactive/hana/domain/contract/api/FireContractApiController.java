package com.interactive.hana.domain.contract.api;

import com.interactive.hana.domain.contract.domain.FireContract;
import com.interactive.hana.domain.contract.dto.FireContractCreateRequest;
import com.interactive.hana.domain.contract.dto.FireContractResponse;
import com.interactive.hana.domain.insurance.domain.FireInsurance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/user/contract/fire")
@RestController
public class FireContractApiController extends
        ContractApiControllerImpl<FireInsurance, FireContractCreateRequest, FireContractResponse, FireContract> {
}
