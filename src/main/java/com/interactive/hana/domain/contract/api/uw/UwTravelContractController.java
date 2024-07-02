package com.interactive.hana.domain.contract.api.uw;

import com.interactive.hana.domain.contract.domain.TravelContract;
import com.interactive.hana.domain.contract.dto.TravelContractResponse;
import com.interactive.hana.domain.insurance.domain.TravelInsurance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/uw/contract/travel")
@RestController
public class UwTravelContractController extends UwContractControllerImpl<TravelInsurance, TravelContractResponse, TravelContract> {
}
