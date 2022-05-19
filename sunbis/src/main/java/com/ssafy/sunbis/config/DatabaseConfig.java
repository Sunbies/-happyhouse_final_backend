package com.ssafy.sunbis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
		basePackages = "com.ssafy.sunbis.model.mapper"
)
public class DatabaseConfig {}