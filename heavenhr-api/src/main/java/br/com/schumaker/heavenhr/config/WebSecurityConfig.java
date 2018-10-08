package br.com.schumaker.heavenhr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
*
* @author Hudson Schumaker
* @version 1.0.0
*/
@Configuration
@EnableWebSecurity //Simple WebSecurityConfiguration for the purpose of the exercise.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("heavenhr").password("secret").roles("USER");
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/api/application/public/apply/**", "/api/candidate/**" ,"/swagger**")
		.permitAll().anyRequest()
		.authenticated()
		.and()
		.httpBasic()
	    .and().csrf().disable();
	}
}