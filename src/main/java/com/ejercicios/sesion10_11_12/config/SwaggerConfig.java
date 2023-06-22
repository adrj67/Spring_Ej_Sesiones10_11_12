
package com.ejercicios.sesion10_11_12.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuracion Swagger para la generacion de documentacion de la API REST
 * 
 * HTML: http://localhost:8080/swagger-ui/
 * 
 * JSON: http://localhost:8080/v2/api-docs
 */

@Configuration
public class SwaggerConfig {
    
    @Bean
    public Docket api(){
        
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    
    private ApiInfo apiDetails(){
        
        return new ApiInfo("Titulo: Spring Boot Laptops API REST",
                "Descripcion: Libreria Api Rest docs",
                "Version: 1.1",
                "URL terminos de Servicio: http://www.google.com",
                new Contact("Adrian","http://www.google.com", "adrian@example.com"),
                "Tipo de licencia: MIT",
                "URL Licencia: http://www.google.com",
                Collections.emptyList());
        
    }
    
}