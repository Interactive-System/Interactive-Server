package com.interactive.hana.domain.accident.service;

import com.interactive.hana.domain.accident.domain.Accident;
import com.interactive.hana.domain.accident.domain.AccidentState;
import com.interactive.hana.domain.accident.dto.AccidentResponse;
import com.interactive.hana.domain.user.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AccidentService {

    void save(List<MultipartFile> files, Long id) throws IOException;

    List<AccidentResponse> accidentListFindByUser(User user);

    List<AccidentResponse> accidentFindByState(AccidentState state);

    Accident findById(Long id);

}
