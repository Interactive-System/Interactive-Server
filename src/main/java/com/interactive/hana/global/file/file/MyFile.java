package com.interactive.hana.global.file.file;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "file")
public class MyFile {

    @Getter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;

    private String fileOriginName;

    private String fileUrl;

    @Builder
    public MyFile(String filename, String fileOriginName, String fileUrl) {
        this.filename = filename;
        this.fileOriginName = fileOriginName;
        this.fileUrl = fileUrl;
    }

}
