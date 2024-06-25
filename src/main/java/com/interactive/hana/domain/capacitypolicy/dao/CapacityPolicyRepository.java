package com.interactive.hana.domain.capacitypolicy.dao;

import com.interactive.hana.domain.capacitypolicy.domain.CapacityPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacityPolicyRepository extends JpaRepository<CapacityPolicy, Long> {
}
