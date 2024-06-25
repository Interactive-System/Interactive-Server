package com.interactive.hana.domain.capacitypolicy.service;

import com.interactive.hana.domain.capacitypolicy.dto.CapacityPolicyCreationRequest;
import com.interactive.hana.domain.capacitypolicy.dto.CapacityPolicyDetailResponse;
import com.interactive.hana.domain.capacitypolicy.dto.CapacityPolicyResponse;
import com.interactive.hana.domain.capacitypolicy.dto.CapacityPolicyUpdateRequest;
import com.interactive.hana.global.dto.PaginationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CapacityPolicyService {

    @Transactional
    void create(CapacityPolicyCreationRequest dto);

    PaginationDto<List<CapacityPolicyResponse>> list(Pageable pageable);

    CapacityPolicyDetailResponse read(Long id);

    @Transactional
    void update(CapacityPolicyUpdateRequest dto);

    @Transactional
    void delete(Long id);

}
