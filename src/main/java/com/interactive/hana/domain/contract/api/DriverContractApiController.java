package com.interactive.hana.domain.contract.api;

import com.interactive.hana.domain.contract.domain.DriverContract;
import com.interactive.hana.domain.contract.dto.DriverContractCreateRequest;
import com.interactive.hana.domain.contract.dto.DriverContractResponse;
import com.interactive.hana.domain.insurance.domain.DriverInsurance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/user/contract/driver")
@RestController
public class DriverContractApiController extends
        ContractApiControllerImpl<DriverInsurance, DriverContractCreateRequest, DriverContractResponse, DriverContract> {
}
