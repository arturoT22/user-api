package cl.ionix.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

	@Value("${path-services-balancer}")
	private String pathServicesBalancer;

	@Bean
	public Docket api(ServletContext servletContext) {

		return new Docket(DocumentationType.SWAGGER_2).
		  				select().
		  				apis(RequestHandlerSelectors.basePackage("cl.ionix")).
		 				paths(PathSelectors.any()).
		  				build().pathProvider(new RelativePathProvider(servletContext) {
							 @Override
							 public String getApplicationBasePath() {
							 	if(pathServicesBalancer != null && !pathServicesBalancer.isEmpty()){
									return pathServicesBalancer.concat(super.getApplicationBasePath());
								}
								 return super.getApplicationBasePath();
							 }
						 });
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
