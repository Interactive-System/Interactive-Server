package com.interactive.hana.domain.consulting.service;

import com.interactive.hana.domain.consulting.dao.ConsultingRepository;
import com.interactive.hana.domain.consulting.domain.Consulting;
import com.interactive.hana.domain.consulting.domain.ConsultingStateType;
import com.interactive.hana.domain.consulting.dto.ConsultingDetailResponse;
import com.interactive.hana.domain.consulting.dto.ConsultingResponse;
import com.interactive.hana.domain.consulting.dto.ConsultingSaveRequest;
import com.interactive.hana.domain.user.dao.UserRepository;
import com.interactive.hana.domain.user.domain.User;
import com.interactive.hana.domain.user.domain.UserRoleType;
import com.interactive.hana.domain.user.exception.UserExceptionMessage;
import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import com.interactive.hana.global.dto.PaginationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ConsultingServiceImpl implements ConsultingService {

    private final UserRepository userRepository;
    private final ConsultingRepository consultingRepository;

    @Transactional
    @Override
    public void create(PrincipalDetails principal, ConsultingSaveRequest dto) {
        User user = this.userRepository.findById(principal.toEntity().getId())
                .orElseThrow(() -> new UsernameNotFoundException(UserExceptionMessage.USERNAME_NOT_FOUND_EXCEPTION_MESSAGE.getMessage()));
        consultingRepository.save(dto.toEntity(user));
    }

    @Override
    public PaginationDto<List<ConsultingResponse>> readAllForUser(Pageable pageable, PrincipalDetails principal) {
        Page<Consulting> page = this.consultingRepository.findAllByUser(principal.toEntity(), pageable);
        List<ConsultingResponse> responses = page.stream().map(ConsultingResponse::from).collect(Collectors.toList());
        return PaginationDto.of(page, responses);
    }

    @Override
    public PaginationDto<List<ConsultingResponse>> readAllForUw(Pageable pageable, ConsultingStateType type) {
        Page<Consulting> page = this.consultingRepository.findAllByState(type, pageable);
        List<ConsultingResponse> responses = page.stream().map(ConsultingResponse::from).collect(Collectors.toList());
        return PaginationDto.of(page, responses);
    }

    @Override
    public ConsultingDetailResponse read(PrincipalDetails principal, Long id) {
        Consulting consulting = this.consultingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상담이 존재하지 않습니다."));
        if (principal.toEntity().getRole().equals(UserRoleType.ROLE_INSURANCE_SELLER) ||
                principal.toEntity().getRole().equals(UserRoleType.ROLE_ADMIN)) {
            return ConsultingDetailResponse.from(consulting);
        }
        if (!principal.getUsername().equals(consulting.getUser().getEmail()))
            throw new IllegalArgumentException("해당 접근 권한이 없습니다.");
        return ConsultingDetailResponse.from(consulting);
    }
}
