package com.humanda6.demoweb2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DemoWebSecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//		// 1 - 1.
//		http.csrf(AbstractHttpConfigurer::disable)
//			.authorizeHttpRequests(
//				(authorize) -> authorize.requestMatchers("/", "/home").permitAll()
//										.requestMatchers("/auth/**").permitAll()
//										.requestMatchers("/main.js", "/image/**", "/styles/**", "/assets/**").permitAll()
//										.anyRequest().authenticated())
//			.httpBasic(Customizer.withDefaults())
//			.formLogin(Customizer.withDefaults());
		
		// 1 - 2.
		http.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(
				(authorize) -> authorize.requestMatchers("/board/**").authenticated()
										.anyRequest().permitAll())
			.httpBasic(AbstractHttpConfigurer::disable)
			.formLogin(form -> form.loginPage("/auth/signin")
								   .usernameParameter("email")
								   .passwordParameter("passwd"));
		
		
		
		return http.build();

	}

}
