package com.example.xml_downloader.controller;

import com.example.xml_downloader.FileReturnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ConfigUpdateController {
    private final FileReturnService fileReturnService;
    @Value("${path.soft}")
    private String path;

    @GetMapping("/software/{fileName}")
    public ResponseEntity configUpdate(
            @PathVariable String fileName
    )
    {
        log.info("fileName: {}", fileName);
        String filePath = path + "/" + fileName;
        log.info("filePath: {}", filePath);

        Path path = Paths.get(filePath);
        log.info("path: {}", path);
        return fileReturnService.fileReturn(path);

    }
}
