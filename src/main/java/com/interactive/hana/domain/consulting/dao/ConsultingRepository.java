package com.interactive.hana.domain.consulting.dao;

import com.interactive.hana.domain.consulting.domain.Consulting;
import com.interactive.hana.domain.consulting.domain.ConsultingStateType;
import com.interactive.hana.domain.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultingRepository extends JpaRepository<Consulting, Long> {

    Page<Consulting> findAllByUser(User user, Pageable pageable);

    Page<Consulting> findAllByState(ConsultingStateType state, Pageable pageable);
}
