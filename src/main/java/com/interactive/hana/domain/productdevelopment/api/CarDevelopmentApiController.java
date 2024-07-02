package com.interactive.hana.domain.productdevelopment.api;

import com.interactive.hana.domain.productdevelopment.domain.CarDevelopment;
import com.interactive.hana.domain.productdevelopment.dto.CarProductDesignRequest;
import com.interactive.hana.domain.productdevelopment.dto.CarProductDevelopmentDetailResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/planner/car/product/development")
@RestController
public class CarDevelopmentApiController extends DevelopmentApiControllerImpl
        <CarProductDesignRequest, CarProductDevelopmentDetailResponse, CarDevelopment> {
}
