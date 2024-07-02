package com.interactive.hana.domain.productdevelopment.service;

import com.interactive.hana.domain.insurance.dao.InsuranceRepository;
import com.interactive.hana.domain.insurance.domain.CarInsurance;
import com.interactive.hana.domain.insurance.exception.DuplicateInsuranceNameException;
import com.interactive.hana.domain.productdevelopment.dao.DevelopmentRepository;
import com.interactive.hana.domain.productdevelopment.domain.CarDevelopment;
import com.interactive.hana.domain.productdevelopment.domain.DevelopmentState;
import com.interactive.hana.domain.productdevelopment.dto.CarProductDesignRequest;
import com.interactive.hana.domain.productdevelopment.dto.CarProductDevelopmentDetailResponse;
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
public class CarDevelopmentServiceImpl implements DevelopmentService<CarProductDesignRequest, CarProductDevelopmentDetailResponse, CarDevelopment> {

    private final DevelopmentRepository<CarDevelopment> developmentRepository;
    private final InsuranceRepository<CarInsurance> carInsuranceRepository;
    private final ListSpecification<CarDevelopment> specification;

    @Override
    public void plan(ProductPlanCreateRequest dto) {
        if (developmentRepository.existsByName(dto.getName()))
            throw new DuplicateInsuranceNameException("이름 중복.");
        CarDevelopment car = dto.toCarProductDevelopmentEntity();
        developmentRepository.save(car);
    }

    @Override
    public void design(CarProductDesignRequest dto) {
        findById(dto.getId()).design(dto);
    }

    @Override
    public void authorize(Long id) {
        findById(id).authorize();
    }

    @Override
    public void approve(Long id) {
        carInsuranceRepository.save(findById(id).approve());
    }

    @Override
    public PaginationDto<List<ProductPlanDevelopmentResponse>> list(Pageable pageable, DevelopmentState state) {
        Specification<CarDevelopment> spec = Specification.where(specification.equalToType("Car")).and(specification.equalToState(state));
        Page<CarDevelopment> page = developmentRepository.findAll(spec, pageable);
        List<ProductPlanDevelopmentResponse> list = page.get().map(CarDevelopment::toResponse).collect(Collectors.toList());
        return PaginationDto.of(page, list);
    }

    @Override
    public CarProductDevelopmentDetailResponse read(Long id) {
        return CarProductDevelopmentDetailResponse.from(findById(id));
    }

    @Override
    public void delete(Long id) {
        this.developmentRepository.delete(findById(id));
    }

    @Override
    public CarDevelopment findById(Long id) {
        return developmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품 개발이 존재하지 않습니다."));
    }

}
