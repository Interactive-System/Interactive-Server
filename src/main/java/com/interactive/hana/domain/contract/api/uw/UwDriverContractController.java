package com.interactive.hana.domain.contract.api.uw;

import com.interactive.hana.domain.contract.domain.DriverContract;
import com.interactive.hana.domain.contract.dto.DriverContractResponse;
import com.interactive.hana.domain.insurance.domain.DriverInsurance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/uw/contract/driver")
@RestController
public class UwDriverContractController extends UwContractControllerImpl<DriverInsurance, DriverContractResponse, DriverContract> {
}
