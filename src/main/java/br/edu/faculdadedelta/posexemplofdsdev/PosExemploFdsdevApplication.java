package br.edu.faculdadedelta.posexemplofdsdev;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;


@SpringBootApplication
public class PosExemploFdsdevApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosExemploFdsdevApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
}
