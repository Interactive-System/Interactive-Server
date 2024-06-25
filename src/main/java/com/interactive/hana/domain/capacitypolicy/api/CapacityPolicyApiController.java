package com.interactive.hana.domain.capacitypolicy.api;

import com.interactive.hana.domain.capacitypolicy.constant.CapacityPolicyConstants;
import com.interactive.hana.domain.capacitypolicy.dto.CapacityPolicyCreationRequest;
import com.interactive.hana.domain.capacitypolicy.dto.CapacityPolicyDetailResponse;
import com.interactive.hana.domain.capacitypolicy.dto.CapacityPolicyResponse;
import com.interactive.hana.domain.capacitypolicy.dto.CapacityPolicyUpdateRequest;
import com.interactive.hana.domain.capacitypolicy.service.CapacityPolicyService;
import com.interactive.hana.global.dto.DefaultResponseDto;
import com.interactive.hana.global.dto.PaginationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/v1/uw/capacity")
@RestController
public class CapacityPolicyApiController {

    private final CapacityPolicyService capacityPolicyService;

    @PostMapping
    public ResponseEntity<DefaultResponseDto> create(@Valid @RequestBody CapacityPolicyCreationRequest dto) {
        this.capacityPolicyService.create(dto);
        return ResponseEntity.ok(DefaultResponseDto.from(CapacityPolicyConstants.COMPLETE_SAVE_CAPACITY_POLICY.getMessage()));
    }

    @GetMapping("list")
    public ResponseEntity<PaginationDto<List<CapacityPolicyResponse>>> list(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(this.capacityPolicyService.list(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<CapacityPolicyDetailResponse> read(@PathVariable Long id) {
        return ResponseEntity.ok(this.capacityPolicyService.read(id));
    }

    @PutMapping
    public ResponseEntity<DefaultResponseDto> update(@Valid @RequestBody CapacityPolicyUpdateRequest dto) {
        this.capacityPolicyService.update(dto);
        return ResponseEntity.ok(DefaultResponseDto.from(CapacityPolicyConstants.COMPLETE_UPDATE_CAPACITY_POLICY.getMessage()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<DefaultResponseDto> delete(@PathVariable Long id) {
        this.capacityPolicyService.delete(id);
        return ResponseEntity.ok(DefaultResponseDto.from(CapacityPolicyConstants.COMPLETE_DELETE_CAPACITY_POLICY.getMessage()));
    }

}
