package com.example.xml_downloader;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileReturnService {

    public ResponseEntity fileReturn(Path path){
        try{
            String contentType = Files.probeContentType(path);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            Resource resource = new InputStreamResource(Files.newInputStream(path));

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        }
        catch (IOException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 파일을 찾을수 없습니다.");
        }
    }
}
