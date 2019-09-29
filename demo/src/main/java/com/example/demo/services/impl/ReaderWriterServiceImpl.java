package com.example.demo.services.impl;

import com.example.demo.models.Product;
import com.example.demo.models.Products;
import com.example.demo.services.ReaderWriterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReaderWriterServiceImpl implements ReaderWriterService {
    @Override
    public List<Product> readFile(String path) {
        List<String> list;
        List<Product> result = new ArrayList<>();
        try {
            URI uri = getClass().getClassLoader().getResource("test.txt").toURI();
            Stream<String> stream = Files.lines(Paths.get(uri));
            list = stream.collect(Collectors.toList());
            result = list.stream().map(this::toProduct).collect(Collectors.toList());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
       return result;
    }

    @Override
    public String jsonFormat() throws JsonProcessingException {
        List<Product> result = this.readFile("");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(result);
    }

    @Override
    public String xmlFormat() throws JAXBException {
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
