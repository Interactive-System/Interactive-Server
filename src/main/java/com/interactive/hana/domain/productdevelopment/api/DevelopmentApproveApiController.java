package com.interactive.hana.domain.productdevelopment.api;

import com.interactive.hana.domain.productdevelopment.domain.DevelopmentState;
import com.interactive.hana.domain.productdevelopment.dto.ProductPlanDevelopmentResponse;
import com.interactive.hana.domain.productdevelopment.service.CarDevelopmentServiceImpl;
import com.interactive.hana.domain.productdevelopment.service.DriverDevelopmentServiceImpl;
import com.interactive.hana.domain.productdevelopment.service.FireDevelopmentServiceImpl;
import com.interactive.hana.domain.productdevelopment.service.TravelDevelopmentServiceImpl;
import com.interactive.hana.global.dto.DefaultResponseDto;
import com.interactive.hana.global.dto.PaginationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/financial/supervisory")
@RestController
public class DevelopmentApproveApiController {

    private final CarDevelopmentServiceImpl carDevelopmentService;
    private final DriverDevelopmentServiceImpl driverDevelopmentService;
    private final FireDevelopmentServiceImpl fireDevelopmentService;
    private final TravelDevelopmentServiceImpl travelDevelopmentService;

    @GetMapping("car/product/development/authorize/list")
    public ResponseEntity<PaginationDto<List<ProductPlanDevelopmentResponse>>> carListByAuthorize(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(carDevelopmentService.list(pageable, DevelopmentState.AUTHORIZE));
    }

    @GetMapping("driver/product/development/authorize/list")
    public ResponseEntity<PaginationDto<List<ProductPlanDevelopmentResponse>>> driverListByAuthorize(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(driverDevelopmentService.list(pageable, DevelopmentState.AUTHORIZE));
    }

    @GetMapping("fire/product/development/authorize/list")
    public ResponseEntity<PaginationDto<List<ProductPlanDevelopmentResponse>>> FireListByAuthorize(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(fireDevelopmentService.list(pageable, DevelopmentState.AUTHORIZE));
    }

    @GetMapping("travel/product/development/authorize/list")
    public ResponseEntity<PaginationDto<List<ProductPlanDevelopmentResponse>>> travelListByAuthorize(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(travelDevelopmentService.list(pageable, DevelopmentState.AUTHORIZE));
    }

    @PutMapping("car/product/development/approve/{id}")
    public ResponseEntity<DefaultResponseDto> approveCar(@PathVariable Long id) {
        carDevelopmentService.approve(id);
        return ResponseEntity.ok(DefaultResponseDto.from("승인 완료."));
    }

    @PutMapping("driver/product/development/approve/{id}")
    public ResponseEntity<DefaultResponseDto> approveDriver(@PathVariable Long id) {
        driverDevelopmentService.approve(id);
        return ResponseEntity.ok(DefaultResponseDto.from("승인 완료."));
    }

    @PutMapping("fire/product/development/approve/{id}")
    public ResponseEntity<DefaultResponseDto> approveFire(@PathVariable Long id) {
        fireDevelopmentService.approve(id);
        return ResponseEntity.ok(DefaultResponseDto.from("승인 완료."));
    }

    @PutMapping("travel/product/development/approve/{id}")
    public ResponseEntity<DefaultResponseDto> approveTravel(@PathVariable Long id) {
        travelDevelopmentService.approve(id);
        return ResponseEntity.ok(DefaultResponseDto.from("승인 완료."));
    }

}
