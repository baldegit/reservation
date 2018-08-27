package com.balde.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	
	
	
//	@Autowired
//	PasswordEncoder passwordEncoder;
//	
//	@Bean
//	public static PasswordEncoder passwordEncoder() {
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		super.configure(auth);
//		auth.jdbcAuthentication()
//		.usersByUsernameQuery("select email,password,active from users where email=?")
//		.authoritiesByUsernameQuery("select u.email, r.role from users u, roles r where u.role_id = r.id and u.email =  ?")
//		.passwordEncoder(passwordEncoder);
//	}
	
	
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;/* pour activer la config qui se trouve dans le fic de properties*/
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.jdbcAuthentication()
		.usersByUsernameQuery("select email,password,active from users where email=?")
		.authoritiesByUsernameQuery("select u.email, r.role from users u, roles r where u.role_id = r.id and u.email =  ?")
		.dataSource(dataSource)
		.passwordEncoder(bCryptPasswordEncoder);
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
	
		http
			.authorizeRequests()
				.antMatchers("/","/home","/editOrCreateNewUser","/saveOrUpdateUser","/catalogueDetail","/getPhoto").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.and()
			.httpBasic();
		
			
	   	
			
//		http
//			.authorizeRequests()
//				.anyRequest().permitAll()
//				.and()
//			.formLogin()
//				.and()
//			.httpBasic();
		//editOrCreateNewUser , /saveOrUpdateUse
//		http
//			.authorizeRequests()
//				.anyRequest().authenticated()
//				.and()
//			.formLogin()
//				.and()
//			.httpBasic();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring()
		.antMatchers("/resources/**","/static/**","/css/**","/js/**","/data/**","/images/**");
	}

}
