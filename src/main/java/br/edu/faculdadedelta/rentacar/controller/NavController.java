package br.edu.faculdadedelta.rentacar.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavController {

	private String message = "Hello World";
	
	@Value("${pagina-inicial}")
	private String paginaInicial;
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return paginaInicial;
	}
	
}
