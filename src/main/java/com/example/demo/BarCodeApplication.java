package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.Controller.BarCodeController;

import io.swagger.v3.oas.models.annotations.OpenAPI30;
import jakarta.servlet.http.HttpServletResponse;

@SpringBootApplication
@OpenAPI30
public class BarCodeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(BarCodeApplication.class, args);
		BarCodeController bean = run.getBean(BarCodeController.class);
		//HttpServletResponse s=new HttpServletResponse();
		//bean.barcode("",s);
	}

}
