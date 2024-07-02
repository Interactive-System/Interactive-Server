package com.interactive.hana.domain.insurance.api;

import com.interactive.hana.domain.insurance.domain.TravelInsurance;
import com.interactive.hana.domain.insurance.dto.TravelInsuranceDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/v1/user/travel")
@RestController
public class TravelInsuranceApiController extends InsuranceApiControllerImpl<TravelInsuranceDetailResponse, TravelInsurance> {
}
