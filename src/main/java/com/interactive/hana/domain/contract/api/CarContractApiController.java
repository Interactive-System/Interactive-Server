package com.interactive.hana.domain.contract.api;

import com.interactive.hana.domain.contract.domain.CarContract;
import com.interactive.hana.domain.contract.dto.CarContractCreateRequest;
import com.interactive.hana.domain.contract.dto.CarContractResponse;
import com.interactive.hana.domain.insurance.domain.CarInsurance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/user/contract/car")
@RestController
public class CarContractApiController extends
        ContractApiControllerImpl<CarInsurance, CarContractCreateRequest, CarContractResponse, CarContract> {
}
