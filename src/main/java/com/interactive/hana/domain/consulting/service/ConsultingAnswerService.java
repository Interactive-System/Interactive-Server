package com.interactive.hana.domain.consulting.service;

import com.interactive.hana.domain.consulting.dto.ConsultingAnswerResponse;
import com.interactive.hana.domain.consulting.dto.ConsultingAnswerSaveRequest;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import org.springframework.transaction.annotation.Transactional;

public interface ConsultingAnswerService {

    @Transactional
    void create(PrincipalDetails principal, ConsultingAnswerSaveRequest dto);

    ConsultingAnswerResponse read(PrincipalDetails principal, Long id);

}
