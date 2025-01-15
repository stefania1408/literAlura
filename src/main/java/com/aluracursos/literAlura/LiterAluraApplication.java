package com.aluracursos.literAlura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.aluracursos.literAlura.console.DriverLiterAlura;
import com.aluracursos.literAlura.service.AuthorSerivece;
import com.aluracursos.literAlura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class LiterAluraApplication  implements CommandLineRunner {


	@Autowired
	BookService bookService;
	@Autowired
	AuthorSerivece authorSerivece;


	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {

		DriverLiterAlura driverLiteratura = new DriverLiterAlura( bookService,authorSerivece);
		driverLiteratura.test();
	}
}



