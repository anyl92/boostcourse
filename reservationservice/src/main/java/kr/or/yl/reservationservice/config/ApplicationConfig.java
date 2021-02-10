package kr.or.yl.reservationservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(
		basePackages = "kr.or.yl.reservationservice", 
		useDefaultFilters = false,
		includeFilters = {
				@Filter(
						type = FilterType.ANNOTATION,
						classes = {Repository.class, Service.class}
						)
				}
		)
@Import({DbConfig.class})
public class ApplicationConfig {

}
