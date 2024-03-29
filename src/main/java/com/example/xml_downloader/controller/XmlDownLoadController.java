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
    private final FileReturnService fileReturnService;
    @Value("${path.xml}")
    private  String path;

    @GetMapping("/ajax/vgw_dbload")
    public ResponseEntity createXml(
            @RequestParam("conffile") String file,
            @RequestParam("device") String tId,
            @RequestParam("nojson") String noJson,
            @RequestParam("mixvendor") String type
    )  {

            String xmlPath = path + tId + "/current.xml";
            Path path = Paths.get(xmlPath);

            return fileReturnService.fileReturn(path);



    }
}
