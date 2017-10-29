# springboot-jsp
An example of Spring Boot application that uses an embedded tomcat and jsp.

## Use JSP in your current Spring Boot project

1. Add required dependencies:

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>jstl</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
</dependency>
<dependency><!-- Optional, use this only when you need spring security taglibs -->
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-taglibs</artifactId>
</dependency>
```

2. Add view prefixes in application properties (yaml example below)

```yml
spring:
  mvc:
    view:
      prefix: /WEB-INF/
      suffix: .jsp
```

3. Configure view resolver:

```java
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${spring.mvc.view.prefix}")
    private String prefix;

    @Value("${spring.mvc.view.suffix}")
    private String suffix;

    /**
     * Configures view resolver for jsp views.
     */
    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix(prefix);
        resolver.setSuffix(suffix);
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
}
```

4. Create jsp views in src/main/webapp/WEB-INF/ directory.

## How to run this application

### Extract project

Extract project in some location on your local machine.

### Build application

Go to the project location:

```bash
cd /path/to/project/location
```

Build project with maven by executing command:

```bash
./mvnw clean install
```

### Start application

Execute .jar file:

```bash
java -jar target/rxjava2-simultaneous-tasks-0.0.1-SNAPSHOT.jar
```

By default application should be accessible under localhost:8080. 

There is one endpoint which returns simple jsp home page.