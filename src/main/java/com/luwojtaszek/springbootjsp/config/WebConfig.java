package com.luwojtaszek.springbootjsp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Stores all web configurations.
 * <p>
 * Created by lukasz on 27.08.2017.
 * With IntelliJ IDEA 15
 */
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${spring.mvc.view.prefix}")
    private String prefix;

    @Value("${spring.mvc.view.suffix}")
    private String suffix;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler(
                "/img/**",
                "/js/**",
                "/css/**",
                "/fonts/**",
                "/libs/**"
        )
                .addResourceLocations(
                        "/WEB-INF/static/img/",
                        "/WEB-INF/static/js/",
                        "/WEB-INF/static/css/",
                        "/WEB-INF/static/fonts/",
                        "/WEB-INF/static/libs/"
                );

        super.addResourceHandlers(registry);
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(prefix);
        resolver.setSuffix(suffix);
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
}
