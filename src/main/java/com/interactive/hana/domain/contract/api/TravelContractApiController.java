package com.interactive.hana.domain.contract.api;

import com.interactive.hana.domain.contract.domain.TravelContract;
import com.interactive.hana.domain.contract.dto.TravelContractCreateRequest;
import com.interactive.hana.domain.contract.dto.TravelContractResponse;
import com.interactive.hana.domain.insurance.domain.TravelInsurance;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/user/contract/travel")
@RestController
public class TravelContractApiController extends
        ContractApiControllerImpl<TravelInsurance, TravelContractCreateRequest, TravelContractResponse, TravelContract> {
}
