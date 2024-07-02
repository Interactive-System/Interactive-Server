package com.interactive.hana.domain.productdevelopment.api;

import com.interactive.hana.domain.productdevelopment.domain.DriverDevelopment;
import com.interactive.hana.domain.productdevelopment.dto.DriverProductDesignRequest;
import com.interactive.hana.domain.productdevelopment.dto.DriverProductDevelopmentDetailResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/planner/driver/product/development")
@RestController
public class DriverDevelopmentApiController extends DevelopmentApiControllerImpl
        <DriverProductDesignRequest, DriverProductDevelopmentDetailResponse, DriverDevelopment> {
}
