package com.interactive.hana.domain.compensation.api;

import com.interactive.hana.domain.compensation.constant.CompensationConstants;
import com.interactive.hana.domain.compensation.dto.CompensationApproveRequest;
import com.interactive.hana.domain.compensation.service.CompensationService;
import com.interactive.hana.global.dto.DefaultResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class CompensationApiController {

    private final com.interactive.hana.domain.compensation.service.CompensationService CompensationService;

    @PostMapping("api/v1/compensation-handler/accident/approve")
    public ResponseEntity<DefaultResponseDto> compensationApprove(@Valid @RequestBody CompensationApproveRequest request) {
        this.CompensationService.compensationApprove(request);
        return ResponseEntity.ok(DefaultResponseDto.from(CompensationConstants.COMPLETE_COMPENSATION_PAID.getMessage()));
    }

    @PutMapping("api/v1/compensation-handler/accident/reject/{id}")
    public ResponseEntity<DefaultResponseDto> compensationReject(@PathVariable Long id) {
        this.CompensationService.compensationReject(id);
        return ResponseEntity.ok(DefaultResponseDto.from(CompensationConstants.REJECT_COMPENSATION.getMessage()));
    }

}