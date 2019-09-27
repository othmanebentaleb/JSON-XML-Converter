package com.example.demo.services;

import com.example.demo.models.Product;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface ReaderWriterService {

    List<Product> readFile(String path) throws JsonProcessingException, JAXBException;
    String jsonFormat() throws JAXBException, JsonProcessingException;
    String xmlFormat() throws JAXBException, JsonProcessingException;
}
