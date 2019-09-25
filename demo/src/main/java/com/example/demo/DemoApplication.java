package com.example.demo;

import com.example.demo.models.Product;
import com.example.demo.services.ReaderWriterService;
import com.example.demo.services.impl.ReaderWriterServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws JsonProcessingException, JAXBException {

		SpringApplication.run(DemoApplication.class, args);

		ReaderWriterService readerWriterService = new ReaderWriterServiceImpl();
		List<Product> list = readerWriterService.readFile("");
	}

}
