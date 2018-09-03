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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
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
			.antMatchers("/","/home","/editOrCreateNewUser","/saveOrUpdateUser","/catalogueDetail","/getPhoto","/login","/connexion","/roles/*").permitAll()
			.antMatchers("/admin/**").hasAuthority("ADMIN")
			.antMatchers("/catalogueReservation").hasAnyAuthority("ADMIN","USER")
			.and().csrf().disable()
		.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/")
			.and()
		.exceptionHandling()
		.accessDeniedPage("/access-denied");

	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring()
		.antMatchers("/resources/**","/static/**","/css/**","/js/**","/data/**","/images/**");
	}

}
