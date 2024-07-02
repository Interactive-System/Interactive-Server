package com.interactive.hana.domain.productdevelopment.api;

import com.interactive.hana.domain.productdevelopment.constant.ProductDevelopmentConstants;
import com.interactive.hana.domain.productdevelopment.domain.DevelopmentState;
import com.interactive.hana.domain.productdevelopment.dto.ProductPlanCreateRequest;
import com.interactive.hana.domain.productdevelopment.dto.ProductPlanDevelopmentResponse;
import com.interactive.hana.domain.productdevelopment.service.DevelopmentService;
import com.interactive.hana.global.dto.DefaultResponseDto;
import com.interactive.hana.global.dto.PaginationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class DevelopmentApiControllerImpl<DesignReq, DetailRes, Entity> implements DevelopmentApiController<DesignReq, DetailRes> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected DevelopmentService<DesignReq, DetailRes, Entity> developmentService;

    @Override
    public ResponseEntity<DefaultResponseDto> plan(ProductPlanCreateRequest dto) {
        this.developmentService.plan(dto);
        return ResponseEntity.ok(DefaultResponseDto.from(ProductDevelopmentConstants.PLAN_SAVE.getMessage()));
    }

    @Override
    public ResponseEntity<DefaultResponseDto> design(DesignReq dto) {
        this.developmentService.design(dto);
        return ResponseEntity.ok(DefaultResponseDto.from(ProductDevelopmentConstants.DESIGN_SAVE.getMessage()));
    }

    @Override
    public ResponseEntity<DefaultResponseDto> authorize(Long id) {
        this.developmentService.authorize(id);
        return ResponseEntity.ok(DefaultResponseDto.from(ProductDevelopmentConstants.COMPLETE_AUTHORIZE.getMessage()));
    }

    @Override
    public ResponseEntity<PaginationDto<List<ProductPlanDevelopmentResponse>>> list(Pageable pageable, DevelopmentState state) {
        return ResponseEntity.ok(this.developmentService.list(pageable, state));
    }

    @Override
    public ResponseEntity<DetailRes> read(Long id) {
        return ResponseEntity.ok(this.developmentService.read(id));
    }

    @Override
    public ResponseEntity<DefaultResponseDto> delete(Long id) {
        this.developmentService.delete(id);
        return ResponseEntity.ok(DefaultResponseDto.from(ProductDevelopmentConstants.DELETE_PRODUCT_DEVELOPMENT.getMessage()));
    }
}
