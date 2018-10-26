package amc.mb.rsassociations.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// registry.addRedirectViewController("/help123", "/help");
		registry.addViewController("/help").setViewName("/help");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/files/**").addResourceLocations("file:/home/mark/temp/img/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/").resourceChain(false);
		// registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/");

	}
}
