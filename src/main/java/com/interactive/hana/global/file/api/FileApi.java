package com.interactive.hana.global.file.api;

import com.interactive.hana.global.file.service.FileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@RequestMapping("api/v1")
@RestController
public class FileApi {

    private final FileService fileService;

    @PostMapping("file-save")
    public ResponseEntity<?> fileSave(@RequestPart MultipartFile files) throws Exception {
        fileService.save(files);
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> userSearch(@PathVariable String name) {
        try (InputStream imageStream = new FileInputStream(System.getProperty("user.dir") + "/images/" + name)) {
            return ResponseEntity.ok(IOUtils.toByteArray(imageStream));
        } catch (IOException e) {
            throw new IllegalArgumentException("해당 파일을 찾을 수 없습니다.");
        }
    }

}
