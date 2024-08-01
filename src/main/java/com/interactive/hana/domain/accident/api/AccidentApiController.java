package com.interactive.hana.domain.accident.api;

import com.interactive.hana.domain.accident.constant.AccidentConstants;
import com.interactive.hana.domain.accident.domain.AccidentState;
import com.interactive.hana.domain.accident.dto.AccidentDetailResponse;
import com.interactive.hana.domain.accident.dto.AccidentResponse;
import com.interactive.hana.domain.accident.service.AccidentService;
import com.interactive.hana.global.dto.DefaultResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccidentApiController {

    private final AccidentService accidentService;

    @PostMapping("api/v1/user/accident/reception/{id}")
    public ResponseEntity<DefaultResponseDto> accidentReception(@RequestParam List<MultipartFile> files,
                                                                @PathVariable Long id) throws IOException {
        accidentService.save(files, id);
        return ResponseEntity.ok(DefaultResponseDto.from(AccidentConstants.COMPLETE_ACCIDENT_RECEPTION.getMessage()));
    }

    @GetMapping("api/v1/compensation-handler/accident/list")
    public ResponseEntity<List<AccidentResponse>> accidentList(@RequestParam AccidentState state) {
        return ResponseEntity.ok(this.accidentService.accidentFindByState(state));
    }

    @GetMapping("api/v1/compensation-handler/accident/detail/{id}")
    public ResponseEntity<AccidentDetailResponse> accidentDetail(@PathVariable Long id) {
        return ResponseEntity.ok(AccidentDetailResponse.from(this.accidentService.findById(id)));
    }
}
