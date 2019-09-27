package com.example.demo.services.impl;

import com.example.demo.models.Product;
import com.example.demo.models.Products;
import com.example.demo.services.ReaderWriterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReaderWriterServiceImpl implements ReaderWriterService {
    @Override
    public List<Product> readFile(String path) throws JsonProcessingException, JAXBException {
        path = "C:\\Users\\X183537\\Desktop\\Test_Technique\\test.txt";
        List<String> list = new ArrayList<>();
        List<Product> result = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))){
            list = stream.collect(Collectors.toList());
            result = list.stream().map(this::toProduct).collect(Collectors.toList());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
       return result;
    }

    @Override
    public String jsonFormat() throws JAXBException, JsonProcessingException {
        List<Product> result = this.readFile("");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(result);
    }

    @Override
    public String xmlFormat() throws JAXBException, JsonProcessingException {
        List<Product> result = this.readFile("");
        Products products = new Products(result);
        JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(products, sw);

        return sw.toString();
    }

    private Product toProduct(String s) {
        return new Product(s);
    }
}
