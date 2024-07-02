package com.interactive.hana.domain.compensation.dao;

import com.interactive.hana.domain.compensation.domain.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompensationRepository extends JpaRepository<Compensation, Long> {
}
