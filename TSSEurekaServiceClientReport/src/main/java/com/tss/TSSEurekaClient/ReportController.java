/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.tss.TSSEurekaClient;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;
import  org.springframework.core.io.InputStreamResource;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource ;
import org.springframework.stereotype.Component;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author mariusz
 */
@RestController
@RequestMapping("/reports")
public class ReportController {
//Przykład:
//// https://www.leveluplunch.com/java/tutorials/032-return-file-from-spring-rest-webservice/     
//ustawienia server.servlet.context-path=/eurekaServiceClient8083Report w application.properties 
//http://localhost:8083/eurekaServiceClient8083Report/reports/userreportpdf
//bez tego url locahost:8083/reports/userreportpdf
//Wywołanie zdalne na serwerze 155.158.112.12
//155.158.112.12:8083/eurekaServiceClient8083Report/reports/userreportpdf
    
private static final Logger log = LoggerFactory.getLogger(ReportController.class);
    
@Autowired
DocumentCreator documentCreator;
    
@RequestMapping(value = "/userreportpdf", method = RequestMethod.GET, produces = "application/pdf")
public ResponseEntity<InputStreamResource> downloadPDFFile()
        throws IOException {
    
    log.info("Tworzenie pdf",System.currentTimeMillis());
    File file = documentCreator.createPDF("");

    //ClassPathResource pdfFile = new ClassPathResource(file.getAbsolutePath());
    FileSystemResource pdfFile = new FileSystemResource(file.getAbsolutePath());    
    log.info("Pobranie pdf",System.currentTimeMillis());
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    headers.add("Content-Disposition","attachment; filename=" + file.getName());
    log.info("Przygotowanie nagłówka H T T P",System.currentTimeMillis());
    return ResponseEntity
            .ok()
            .headers(headers)
            .contentLength(pdfFile.contentLength())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(new InputStreamResource(pdfFile.getInputStream()));
}


    
}
