package com.example.demo.controllers;

import com.example.demo.services.ReaderWriterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;

@RestController
@CrossOrigin("http://localhost:4300")
public class ReaderWriterController {

    @Autowired
    private ReaderWriterService readerWriterService;

    @GetMapping(value = "/json")
    public String getFileContentJSON() throws JAXBException, JsonProcessingException {
        return this.readerWriterService.jsonFormat();
    }

    @GetMapping(value = "xml")
    public String getFileContentXML() throws JAXBException, JsonProcessingException {
        return this.readerWriterService.xmlFormat();
    }

}
