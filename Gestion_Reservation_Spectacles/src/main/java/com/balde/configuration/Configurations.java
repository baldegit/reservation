package com.balde.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class Configurations{
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
//	@Bean
//	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver,
//			SpringSecurityDialect sec) {
//		final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		templateEngine.setTemplateResolver(templateResolver);
//		templateEngine.addDialect(sec);
//		return templateEngine;
//	}
	
//	@Autowired
//	LocaliteFormatter localiteFormatter;
//	
//	public void addLocaliteFormatters(FormatterRegistry registry) {
//		registry.addFormatter(localiteFormatter);
//	}
		
}
