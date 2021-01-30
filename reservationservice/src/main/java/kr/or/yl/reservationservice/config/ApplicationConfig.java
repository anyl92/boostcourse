package kr.or.yl.reservationservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {
		"kr.or.yl.reservationservice.category.dao", "kr.or.yl.reservationservice.category.service",
		"kr.or.yl.reservationservice.config", "kr.or.yl.reservationservice.product"
	})
@Import({DBConfig.class})
public class ApplicationConfig {

}
