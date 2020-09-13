package com.dml.topup.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;

/**
 * Loads the spring beans required by the framework
 *
 * @author Ismael Sadeghi
 */
@Configuration
@EnableSwagger2
@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

    /**
     * Every Docket bean is picked up by the swagger-mvc framework - allowing for multiple swagger
     * groups i.e. same code base multiple swagger resource listings.
     */
    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
//                .host("http://localhost:8080") // change port
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Date.class, Long.class)
                .useDefaultResponseMessages(true)
                .enableUrlTemplating(false);
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("Data Mining Leaders", "http://www.dmlcc.com", "info@dmlcc.com");
        return new ApiInfoBuilder()
                .title("Dml")
                .description("Dml webservices documentation")
                .version("1.0.0")
                .termsOfServiceUrl("http://www.dmlcc.com")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .contact(contact)
                .build();
    }

}