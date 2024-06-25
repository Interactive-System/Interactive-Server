package com.interactive.hana.infra.mail.dao;

import com.interactive.hana.infra.mail.domain.EmailAuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailAuthCodeRepository extends JpaRepository<EmailAuthCode, Long> {

    Optional<EmailAuthCode> findByEmail(String email);

    boolean existsByEmail(String email);

}
