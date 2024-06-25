package com.interactive.hana.domain.contract.api.uw;

import com.interactive.hana.domain.contract.domain.CarContract;
import com.interactive.hana.domain.contract.dto.CarContractResponse;
import com.interactive.hana.domain.insurance.domain.CarInsurance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/uw/contract/car")
@RestController
public class UwCarContractController extends UwContractControllerImpl<CarInsurance, CarContractResponse, CarContract> {
}
