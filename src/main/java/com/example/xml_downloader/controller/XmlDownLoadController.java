package com.example.xml_downloader.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequiredArgsConstructor
@Slf4j
public class XmlDownLoadController {
    @Value("${xml.path}")
    private  String path;

    @GetMapping("/ajax/vgw_dbload")
    public ResponseEntity createXml(
            @RequestParam("conffile") String file,
            @RequestParam("device") String tId,
            @RequestParam("nojson") String noJson,
            @RequestParam("mixvendor") String type
    )  {
        try{
            String xmlPath = path + tId + "/current.xml";
            Path path = Paths.get(xmlPath);
            String contentType = Files.probeContentType(path);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            Resource resource = new InputStreamResource(Files.newInputStream(path));

            return new ResponseEntity<>(resource, headers, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("해당 XML파일을 찾을수 없습니다.");
        }


    }
}
