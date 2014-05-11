package edu.nyu.cs9033.configurations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;

/**
 * @author Shilpan Patel
 * 
 */

@Configuration
public class JacksonConfig extends WebMvcConfigurationSupport {

	private static final String[] SERVLET_RESOURCE_LOCATIONS = { "/" };

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/static/", "classpath:/public/" };

	private static final String[] RESOURCE_LOCATIONS;
	static {
		RESOURCE_LOCATIONS = new String[CLASSPATH_RESOURCE_LOCATIONS.length
				+ SERVLET_RESOURCE_LOCATIONS.length];
		System.arraycopy(SERVLET_RESOURCE_LOCATIONS, 0, RESOURCE_LOCATIONS, 0,
				SERVLET_RESOURCE_LOCATIONS.length);
		System.arraycopy(CLASSPATH_RESOURCE_LOCATIONS, 0, RESOURCE_LOCATIONS,
				SERVLET_RESOURCE_LOCATIONS.length,
				CLASSPATH_RESOURCE_LOCATIONS.length);
	}

	private static final String[] STATIC_INDEX_HTML_RESOURCES;
	static {
		STATIC_INDEX_HTML_RESOURCES = new String[RESOURCE_LOCATIONS.length];
		for (int i = 0; i < STATIC_INDEX_HTML_RESOURCES.length; i++) {
			STATIC_INDEX_HTML_RESOURCES[i] = RESOURCE_LOCATIONS[i]
					+ "index.html";
		}
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**")
					.addResourceLocations(RESOURCE_LOCATIONS).setCachePeriod(0);
		}
	}

	protected void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(new MappingJackson2HttpMessageConverter());
		addDefaultHttpMessageConverters(converters);
	}

}