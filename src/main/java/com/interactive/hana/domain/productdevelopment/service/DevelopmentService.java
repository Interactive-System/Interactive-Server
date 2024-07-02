package com.interactive.hana.domain.productdevelopment.service;

import com.interactive.hana.domain.productdevelopment.domain.DevelopmentState;
import com.interactive.hana.domain.productdevelopment.dto.ProductPlanCreateRequest;
import com.interactive.hana.domain.productdevelopment.dto.ProductPlanDevelopmentResponse;
import com.interactive.hana.global.dto.PaginationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DevelopmentService<DesignReq, DetailRes, Entity> {

    void plan(ProductPlanCreateRequest dto);

    @Transactional
    void design(DesignReq dto);

    @Transactional
    void authorize(Long id);

    @Transactional
    void approve(Long id);

    PaginationDto<List<ProductPlanDevelopmentResponse>> list(Pageable pageable, DevelopmentState state);

    DetailRes read(Long id);

    Entity findById(Long id);

    void delete(Long id);

}
