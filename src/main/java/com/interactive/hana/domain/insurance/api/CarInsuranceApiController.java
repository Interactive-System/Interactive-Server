package com.interactive.hana.domain.insurance.api;

import com.interactive.hana.domain.insurance.domain.CarInsurance;
import com.interactive.hana.domain.insurance.dto.CarInsuranceDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/user/car")
@RestController
public class CarInsuranceApiController extends InsuranceApiControllerImpl<CarInsuranceDetailResponse, CarInsurance> {
}