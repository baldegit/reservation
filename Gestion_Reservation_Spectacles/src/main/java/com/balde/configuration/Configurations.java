package com.balde.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Configurations{
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
//	@Autowired
//	LocaliteFormatter localiteFormatter;
//	
//	public void addLocaliteFormatters(FormatterRegistry registry) {
//		registry.addFormatter(localiteFormatter);
//	}
		
}
