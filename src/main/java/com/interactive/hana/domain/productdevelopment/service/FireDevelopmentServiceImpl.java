package com.interactive.hana.domain.productdevelopment.service;

import com.interactive.hana.domain.insurance.dao.InsuranceRepository;
import com.interactive.hana.domain.insurance.domain.FireInsurance;
import com.interactive.hana.domain.insurance.exception.DuplicateInsuranceNameException;
import com.interactive.hana.domain.productdevelopment.dao.DevelopmentRepository;
import com.interactive.hana.domain.productdevelopment.domain.DevelopmentState;
import com.interactive.hana.domain.productdevelopment.domain.FireDevelopment;
import com.interactive.hana.domain.productdevelopment.dto.FireProductDesignRequest;
import com.interactive.hana.domain.productdevelopment.dto.FireProductDevelopmentDetailResponse;
import com.interactive.hana.domain.productdevelopment.dto.ProductPlanCreateRequest;
import com.interactive.hana.domain.productdevelopment.dto.ProductPlanDevelopmentResponse;
import com.interactive.hana.global.dto.PaginationDto;
import com.interactive.hana.global.util.ListSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FireDevelopmentServiceImpl implements DevelopmentService<FireProductDesignRequest, FireProductDevelopmentDetailResponse, FireDevelopment> {

    private final DevelopmentRepository<FireDevelopment> developmentRepository;
    private final InsuranceRepository<FireInsurance> fireInsuranceRepository;
    private final ListSpecification<FireDevelopment> specification;

    @Override
    public void plan(ProductPlanCreateRequest dto) {
        if (developmentRepository.existsByName(dto.getName()))
            throw new DuplicateInsuranceNameException("이름 중복.");
        developmentRepository.save(dto.toFireProductDevelopmentEntity());
    }

    @Override
    public void design(FireProductDesignRequest dto) {
        findById(dto.getId()).design(dto);
    }

    @Override
    public void authorize(Long id) {
        findById(id).authorize();
    }

    @Override
    public void approve(Long id) {
        fireInsuranceRepository.save(findById(id).approve());
    }

    @Override
    public PaginationDto<List<ProductPlanDevelopmentResponse>> list(Pageable pageable, DevelopmentState state) {
        Specification<FireDevelopment> spec = Specification.where(specification.equalToType("Fire")).and(specification.equalToState(state));
        Page<FireDevelopment> page = developmentRepository.findAll(spec, pageable);
        List<ProductPlanDevelopmentResponse> list = page.get().map(FireDevelopment::toResponse).collect(Collectors.toList());
        return PaginationDto.of(page, list);
    }

    @Override
    public FireProductDevelopmentDetailResponse read(Long id) {
        return FireProductDevelopmentDetailResponse.from(findById(id));
    }

    @Override
    public void delete(Long id) {
        this.developmentRepository.delete(findById(id));
    }

    @Override
    public FireDevelopment findById(Long id) {
        return developmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품 개발이 존재하지 않습니다."));
    }

}
