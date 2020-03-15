package top.lsyweb.hosadm.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import top.lsyweb.hosadm.util.PathUtil;

import java.util.Properties;

/**
 * @Auther: Erekilu
 * @Date: 2020-03-08
 */
@Configuration
public class StaticConfig implements WebMvcConfigurer
{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/hospital_admin/**").addResourceLocations("file:" + PathUtil.getBasePath());
	}
}
