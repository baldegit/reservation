package com.balde.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;

import com.balde.service.LocaliteFormatter;

@Configuration
public class Configurations {
	
	@Autowired
	LocaliteFormatter localiteFormatter;
	
	public void addLocaliteFormatters(FormatterRegistry registry) {
		registry.addFormatter(localiteFormatter);
	}
	
	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//		return bCryptPasswordEncoder;
//	}
}
