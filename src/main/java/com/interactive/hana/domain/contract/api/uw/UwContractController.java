package com.interactive.hana.domain.contract.api.uw;

import com.interactive.hana.global.config.security.auth.PrincipalDetails;
import com.interactive.hana.global.dto.DefaultResponseDto;
import com.interactive.hana.global.dto.PaginationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface UwContractController<Res> {

    @GetMapping("wait/list")
    ResponseEntity<PaginationDto<List<Res>>> dueProcessWaitList(@PageableDefault Pageable pageable);

    @GetMapping("wait/detail/{id}")
    ResponseEntity<Res> read(@AuthenticationPrincipal PrincipalDetails principal, @PathVariable Long id);

    @PutMapping("approve/{id}")
    ResponseEntity<DefaultResponseDto> uwApprove(@AuthenticationPrincipal PrincipalDetails principal, @PathVariable Long id);

    @PutMapping("reject/{id}")
    ResponseEntity<DefaultResponseDto> uwReject(@AuthenticationPrincipal PrincipalDetails principal, @PathVariable Long id);

}
