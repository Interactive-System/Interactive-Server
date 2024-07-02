package com.interactive.hana.domain.insurance.api;

import com.interactive.hana.domain.insurance.domain.DriverInsurance;
import com.interactive.hana.domain.insurance.dto.DriverInsuranceDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/user/driver")
@RestController
public class DriverInsuranceApiController extends InsuranceApiControllerImpl<DriverInsuranceDetailResponse, DriverInsurance> {
}
