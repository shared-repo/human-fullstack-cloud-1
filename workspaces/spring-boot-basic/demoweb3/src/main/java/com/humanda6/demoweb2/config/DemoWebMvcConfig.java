package com.humanda6.demoweb2.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.humanda6.demoweb2.interceptor.AuthInterceptor;

@Configuration
public class DemoWebMvcConfig implements WebMvcConfigurer { // web mvc 설정 클래스

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		
//		registry.addInterceptor(new AuthInterceptor())
//				.addPathPatterns("/board/**");
//		
//	}
	
	@Bean
	public JavaMailSender mailSender() {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.naver.com");
		mailSender.setPort(465);
		mailSender.setDefaultEncoding("utf-8");
		mailSender.setUsername("ohchihooooon");
		mailSender.setPassword("75224K8YJ64G");
		
		Properties props = new Properties();
		props.put("mail.debug", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.starttls.required", true);
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.ssl.enable", true);
		props.put("mail.smtp.ssl.trust", true);
		mailSender.setJavaMailProperties(props);
		
		return mailSender;
	}
	
}