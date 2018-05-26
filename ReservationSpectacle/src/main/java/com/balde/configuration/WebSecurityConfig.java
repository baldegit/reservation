//package com.balde.configuration;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		auth.inMemoryAuthentication()
//		.withUser("balde").password("balde").roles("USER").and()
//		.withUser("diallo").password("diallo").roles("ADMIN");
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
//		http
//		.authorizeRequests()
//			.antMatchers("/home").permitAll()
//			.antMatchers("/display").hasRole("ADMIN")
//			.and()
//		.formLogin()
//			.loginPage("/login")
//			.permitAll();
//	}
//}
