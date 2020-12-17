package com.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
			.antMatchers("/api/v1/student/getAll").permitAll()
			.antMatchers("/api/v1/student/get").hasAnyAuthority("STUDENT","EMPLOYEE","ADMIN")
			.antMatchers("/v2/api-docs",
	                "/configuration/ui",
	                "/swagger-resources/**",
	                "/configuration/security",
	                "/swagger-ui.html",
	                "/webjars/**,/api/v1/student/getAll").permitAll()
	        //.authenticated().and().httpBasic()
			
			
	        .anyRequest().authenticated()
			
			
			//Form Details
			.and()
			.formLogin()
			.defaultSuccessUrl("/swagger-ui.html",true)
			//Logout
			.and()
			.logout()
			
			;
		//http.csrf().disable();
		
			
			
	}
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		 web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/v3/api-docs/**",
//	                "/swagger-ui/**", "/swagger-ui/index.html/**");
//	    }
	
	

}

