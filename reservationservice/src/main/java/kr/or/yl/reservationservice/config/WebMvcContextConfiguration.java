package kr.or.yl.reservationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
		"kr.or.yl.reservationservice.main.controller",
//		"kr.or.yl.reservationservice.product.controller",
//		"kr.or.yl.reservationservice.promotion.controller",
//		"kr.or.yl.reservationservice.reservation.controller",
		"kr.or.yl.reservationservice.config"
})
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter{
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("/css/**")
			.addResourceLocations("/css/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("/img/**")
			.addResourceLocations("/img/")
			.setCachePeriod(31556926);
		registry.addResourceHandler("/js/**")
			.addResourceLocations("/js/")
			.setCachePeriod(31556926);
	}

	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver getInstanceResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

}
