package com.interactive.hana.domain.productdevelopment.api;

import com.interactive.hana.domain.productdevelopment.domain.TravelDevelopment;
import com.interactive.hana.domain.productdevelopment.dto.TravelProductDesignRequest;
import com.interactive.hana.domain.productdevelopment.dto.TravelProductDevelopmentDetailResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/planner/travel/product/development")
@RestController
public class TravelDevelopmentApiController extends DevelopmentApiControllerImpl
        <TravelProductDesignRequest, TravelProductDevelopmentDetailResponse, TravelDevelopment> {
}
