package com.interactive.hana.global.file.dao;

import com.interactive.hana.global.file.file.MyFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<MyFile, Long> {
}
