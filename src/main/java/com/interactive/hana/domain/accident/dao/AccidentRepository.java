package com.interactive.hana.domain.accident.dao;

import com.interactive.hana.domain.accident.domain.Accident;
import com.interactive.hana.domain.accident.domain.AccidentState;
import com.interactive.hana.domain.contract.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccidentRepository extends JpaRepository<Accident, Long> {

    List<Accident> findAllByContract(Contract<?> contract);

    List<Accident> findAllByState(AccidentState state);

}
