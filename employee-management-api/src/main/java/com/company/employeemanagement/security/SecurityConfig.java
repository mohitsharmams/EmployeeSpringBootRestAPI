package com.company.employeemanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.company.employeemanagement.service.MyUserDetailsService;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.myUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests()
		.antMatchers("/h2-console/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/users/**", "/api/roles/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_OPERATOR")
		.antMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.DELETE, "/api/**").hasAnyAuthority("ROLE_ADMIN")
		.antMatchers(HttpMethod.GET,"/api/employees", "/api/employees/").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
		.anyRequest().authenticated()
		.and()
		.httpBasic()
		.and()
		.formLogin();
	}
	//Old method to exclude H2-Console from Spring Security
//  @Bean
//  public WebSecurityCustomizer webSecurityCustomizer() {
//      return (web) -> web.ignoring().antMatchers("/h2-console/**");
//  }
}