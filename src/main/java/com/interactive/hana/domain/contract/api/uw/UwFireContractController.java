package com.interactive.hana.domain.contract.api.uw;

import com.interactive.hana.domain.contract.domain.FireContract;
import com.interactive.hana.domain.contract.dto.FireContractResponse;
import com.interactive.hana.domain.insurance.domain.FireInsurance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/uw/contract/fire")
@RestController
public class UwFireContractController extends UwContractControllerImpl<FireInsurance, FireContractResponse, FireContract> {
}
