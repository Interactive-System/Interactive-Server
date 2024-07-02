package com.interactive.hana.domain.productdevelopment.service;

import com.interactive.hana.domain.insurance.dao.InsuranceRepository;
import com.interactive.hana.domain.insurance.domain.TravelInsurance;
import com.interactive.hana.domain.insurance.exception.DuplicateInsuranceNameException;
import com.interactive.hana.domain.productdevelopment.dao.DevelopmentRepository;
import com.interactive.hana.domain.productdevelopment.domain.DevelopmentState;
import com.interactive.hana.domain.productdevelopment.domain.TravelDevelopment;
import com.interactive.hana.domain.productdevelopment.dto.ProductPlanCreateRequest;
import com.interactive.hana.domain.productdevelopment.dto.ProductPlanDevelopmentResponse;
import com.interactive.hana.domain.productdevelopment.dto.TravelProductDesignRequest;
import com.interactive.hana.domain.productdevelopment.dto.TravelProductDevelopmentDetailResponse;
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
public class TravelDevelopmentServiceImpl implements DevelopmentService<TravelProductDesignRequest, TravelProductDevelopmentDetailResponse, TravelDevelopment> {

    private final DevelopmentRepository<TravelDevelopment> developmentRepository;
    private final InsuranceRepository<TravelInsurance> travelInsuranceRepository;
    private final ListSpecification<TravelDevelopment> specification;

    @Override
    public void plan(ProductPlanCreateRequest dto) {
        if (developmentRepository.existsByName(dto.getName()))
            throw new DuplicateInsuranceNameException("이름 중복.");
        developmentRepository.save(dto.toTravelProductDevelopmentEntity());
    }

    @Override
    public void design(TravelProductDesignRequest dto) {
        findById(dto.getId()).design(dto);
    }

    @Override
    public void authorize(Long id) {
        findById(id).authorize();
    }

    @Override
    public void approve(Long id) {
        this.travelInsuranceRepository.save(findById(id).approve());
    }

    @Override
    public PaginationDto<List<ProductPlanDevelopmentResponse>> list(Pageable pageable, DevelopmentState state) {
        Specification<TravelDevelopment> spec = Specification.where(specification.equalToType("Travel")).and(specification.equalToState(state));
        Page<TravelDevelopment> page = developmentRepository.findAll(spec, pageable);
        List<ProductPlanDevelopmentResponse> list = page.get().map(TravelDevelopment::toResponse).collect(Collectors.toList());
        return PaginationDto.of(page, list);
    }

    @Override
    public TravelProductDevelopmentDetailResponse read(Long id) {
        return TravelProductDevelopmentDetailResponse.from(developmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품 개발이 존재하지 않습니다.")));
    }

    @Override
    public void delete(Long id) {
        this.developmentRepository.delete(findById(id));
    }

    @Override
    public TravelDevelopment findById(Long id) {
        return developmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품 개발이 존재하지 않습니다."));
    }

}
