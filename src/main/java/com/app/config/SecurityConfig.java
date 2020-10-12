package com.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		    .withUser("SAM")
		    .password("{noop}SAM")
		    .authorities("STUDENT");
		auth.inMemoryAuthentication()
			.withUser("KHAN")
			.password("{noop}KHAN")
			.authorities("EMPLOYEE");
		auth.inMemoryAuthentication()
			.withUser("RAM")
			.password("{noop}RAM")
			.authorities("ADMIN");
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/getAll").permitAll()
			.antMatchers("/get").hasAnyAuthority("STUDENT","EMPLOYEE","ADMIN")
			.anyRequest().authenticated()
			
			//Form Details
			.and()
			.formLogin()
			.defaultSuccessUrl("/getAll",true)
			//Logout
			.and()
			.logout()
			
			;
			
			
	}

}

