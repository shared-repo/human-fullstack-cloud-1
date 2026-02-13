package com.myweb.bootmywebref.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // root-context.xml 파일과 같은 설정을 위한 클래스
public class DatabaseConfig {
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari") // application.properties의 설정 값을 읽어서 자동으로 적용
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		return config; // 반환되는 객체에 설정이 자동으로 적용됩니다.
	}
	
	@Bean // <bean id="..." 와 같은 기능
	public DataSource dataSource() {
		
		return new HikariDataSource(hikariConfig());
	}

}
