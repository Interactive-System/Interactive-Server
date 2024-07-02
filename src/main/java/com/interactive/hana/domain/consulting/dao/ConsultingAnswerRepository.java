package com.interactive.hana.domain.consulting.dao;

import com.interactive.hana.domain.consulting.domain.ConsultingAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultingAnswerRepository extends JpaRepository<ConsultingAnswer, Long> {
}
