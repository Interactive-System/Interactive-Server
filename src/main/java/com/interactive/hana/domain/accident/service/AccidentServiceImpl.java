package com.interactive.hana.domain.accident.service;

import com.interactive.hana.domain.accident.dao.AccidentRepository;
import com.interactive.hana.domain.accident.domain.Accident;
import com.interactive.hana.domain.accident.domain.AccidentState;
import com.interactive.hana.domain.accident.dto.AccidentResponse;
import com.interactive.hana.domain.accident.exception.AccidentExceptionMessages;
import com.interactive.hana.domain.accident.exception.AccidentNotFoundException;
import com.interactive.hana.domain.contract.dao.ContractRepository;
import com.interactive.hana.domain.contract.domain.Contract;
import com.interactive.hana.domain.contract.exception.ContractExceptionMessages;
import com.interactive.hana.domain.contract.exception.ContractNotFoundException;
import com.interactive.hana.domain.user.domain.User;
import com.interactive.hana.global.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccidentServiceImpl implements AccidentService {

    private final AccidentRepository accidentRepository;
    private final ContractRepository<?> contractRepository;
    private final FileService fileService;

    @Override
    public void save(List<MultipartFile> files, Long id) throws IOException {
        Contract<?> contract = this.contractRepository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException(ContractExceptionMessages.CONTRACT_NOT_FOUND_EXCEPTION));
        Accident accident = new Accident(contract);
        for (MultipartFile file : files) {
            accident.addFile(this.fileService.save(file));
        }
        this.accidentRepository.save(accident);
    }

    @Override
    public List<AccidentResponse> accidentListFindByUser(User user) {
        List<AccidentResponse> userAccidentList = new ArrayList<>();
        user.getContractList().forEach(contract -> userAccidentList.addAll(this.accidentRepository.findAllByContract(contract)
                .stream().map(AccidentResponse::from).collect(Collectors.toList())));
        return userAccidentList;
    }

    @Override
    public List<AccidentResponse> accidentFindByState(AccidentState state) {
        return this.accidentRepository.findAllByState(state).stream().map(AccidentResponse::from).collect(Collectors.toList());
    }

    @Override
    public Accident findById(Long id) {
        return this.accidentRepository.findById(id).orElseThrow(() -> new AccidentNotFoundException(AccidentExceptionMessages.ACCIDENT_NOT_FOUND_EXCEPTION_MESSAGE));
    }

}
