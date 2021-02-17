package kr.or.yl.reservationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import kr.or.yl.reservationservice.main.interceptor.LogInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(
		basePackages = "kr.or.yl.reservationservice", 
		useDefaultFilters = false,
		includeFilters = {
				@Filter(
						type = FilterType.ANNOTATION,
						classes = {Controller.class}
						)
				}
		)
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
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor());
	}

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(10485760); // 1024 * 1024 * 10
        return multipartResolver;
    }
}
