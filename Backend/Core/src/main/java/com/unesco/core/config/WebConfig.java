package com.unesco.core.config;

import com.googlecode.flyway.core.Flyway;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Collections;
import java.util.Map;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/", "classpath:/public/vendor/" };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Bean
    ErrorViewResolver supportPathBasedLocationStrategyWithoutHashes() {
        return new ErrorViewResolver() {
            @Override
            public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
                if(status == HttpStatus.NOT_FOUND || status == HttpStatus.FORBIDDEN)
                    return  new ModelAndView("index.html", Collections.<String, Object>emptyMap(), HttpStatus.OK);
                return null;
            }
        };
    }

    // При запуске проекта проверяет существуют ли миграции.
    @Bean(initMethod = "migrate")
    Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setInitOnMigrate(true);
        return flyway;
    }

    // Решения добовляющее возможность
    // провести выполнение миграции до проверки соотвествия сущеностей базе данных.
    @Bean
    @DependsOn("flyway")
    protected BeanPostProcessor forceFlywayToInitialize() {

        return new BeanPostProcessor() {

            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName)
                    throws BeansException {
                return bean;
            }

            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName)
                    throws BeansException {
                return bean;
            }

        };
    }
}