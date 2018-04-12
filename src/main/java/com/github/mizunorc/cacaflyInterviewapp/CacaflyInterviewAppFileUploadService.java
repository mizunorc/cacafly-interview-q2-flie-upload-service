package com.github.mizunorc.cacaflyInterviewapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@SpringBootApplication
@EnableOAuth2Sso
public class CacaflyInterviewAppFileUploadService extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(CacaflyInterviewAppFileUploadService.class, args);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.antMatcher("/**").authorizeRequests()
		.antMatchers("/login**","/webjars/**").permitAll()
		.anyRequest().authenticated()
		.and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/loginroot"))
		.and().logout().logoutSuccessUrl("/loginroot").permitAll();;
	}
}
