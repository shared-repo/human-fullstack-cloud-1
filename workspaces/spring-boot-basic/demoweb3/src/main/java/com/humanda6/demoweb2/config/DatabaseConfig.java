package com.humanda6.demoweb2.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // -> servlet-context.xml 과 같은 설정파일 클래스로 구현
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari") // application.properties에서 가져오기
	public HikariConfig hikariConfig() {
		
		HikariConfig config = new HikariConfig();
//		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		config.setJdbcUrl("jdbc:mysql://192.168.0.158:3306/demoweb3");
//		config.setUsername("root");
//		config.setPassword("mysql");
		
		return config;
	}
	
	@Bean // --> servlet-context.xml과 같은 설정 파일의 <bean에 해당
	public DataSource dataSource() {		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig());
		
		return dataSource;
	}	
	

}