package com.humanda6.demoweb2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.humanda6.demoweb2.security.DemoWebPasswordEncoder;
import com.humanda6.demoweb2.security.DemoWebUserDetailsService;

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
		
//		// 1 - 2.
//		http.csrf(AbstractHttpConfigurer::disable)
//			.authorizeHttpRequests(
//				(authorize) -> authorize.requestMatchers("/board/**").authenticated()
//										.anyRequest().permitAll())
//			.httpBasic(AbstractHttpConfigurer::disable)
//			.formLogin(form -> form.loginPage("/auth/signin")
//								   .usernameParameter("email")
//								   .passwordParameter("passwd"));
		
		http
		.csrf(AbstractHttpConfigurer::disable)
		.authorizeHttpRequests( auth -> auth.requestMatchers("/board/write", "/board/delete", "/board/edit", "/board/update").authenticated()
											.requestMatchers("/board/write-comment", "/board/write-recomment", "/board/delete-comment", "/board/update-comment").authenticated()
											.anyRequest().permitAll() )
		.httpBasic(AbstractHttpConfigurer::disable)
		.formLogin( form -> form.loginPage("/auth/signin")
								.usernameParameter("email")
								.passwordParameter("passwd")
								.loginProcessingUrl("/auth/do-signin") )
		.logout( logout -> logout.logoutUrl("/auth/signout")
								 .invalidateHttpSession(true)
								 .deleteCookies("JSESSIONID")
								 .logoutSuccessUrl("/index"));   
		
		return http.build();

	}
	
	@Bean  PasswordEncoder passwordEncoder() {
		return new DemoWebPasswordEncoder();
	}
	
	@Bean  UserDetailsService userDetailService() {
		return new DemoWebUserDetailsService();
	}

}















