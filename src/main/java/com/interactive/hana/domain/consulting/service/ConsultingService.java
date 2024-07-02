package com.interactive.hana.domain.consulting.service;

import com.interactive.hana.domain.consulting.domain.ConsultingStateType;
import com.interactive.hana.domain.consulting.dto.ConsultingDetailResponse;
import com.interactive.hana.domain.consulting.dto.ConsultingResponse;
import com.interactive.hana.domain.consulting.dto.ConsultingSaveRequest;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import com.interactive.hana.global.dto.PaginationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConsultingService {

    void create(PrincipalDetails principal, ConsultingSaveRequest dto);

    PaginationDto<List<ConsultingResponse>> readAllForUser(Pageable pageable, PrincipalDetails principal);

    PaginationDto<List<ConsultingResponse>> readAllForUw(Pageable pageable, ConsultingStateType type);

    ConsultingDetailResponse read(PrincipalDetails principal, Long id);

}
