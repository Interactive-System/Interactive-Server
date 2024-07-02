package com.interactive.hana.domain.productdevelopment.api;

import com.interactive.hana.domain.productdevelopment.domain.FireDevelopment;
import com.interactive.hana.domain.productdevelopment.dto.FireProductDesignRequest;
import com.interactive.hana.domain.productdevelopment.dto.FireProductDevelopmentDetailResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/planner/fire/product/development")
@RestController
public class FireDevelopmentApiController extends DevelopmentApiControllerImpl
        <FireProductDesignRequest, FireProductDevelopmentDetailResponse, FireDevelopment> {
}
