package com.myweb.bootmywebref.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 설정 클래스로 등록
public class MyWebConfig 
	implements WebMvcConfigurer { // 웹 설정 클래스 : legacy spring의 web.xml과 servlet-context.xml 역할 

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/pictures/**")
				.addResourceLocations("file:/work/upload-pictures/");
	}
	
}
