package com.korit.springboot_study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean   // Configuration 이 생성되면 Bean 이 같이 생성된다 (설정 객체이기 때문에)
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // 해당 패키지 안에 있는 모든 컨트롤러 Swagger 적용
                .apis(RequestHandlerSelectors.basePackage("com.korit.springboot_study.controller"))
                .paths(PathSelectors.any()) // 모든 주소 및 URL Swagger 적용
                .build()
                .apiInfo(getApiInfo())
                .securitySchemes(Arrays.asList(getApiKey()))
                .securityContexts(Arrays.asList(getSecurityContext()));

    }

    private ApiInfo getApiInfo() {

        return new ApiInfoBuilder()
                .title("API 문서 제목")
                .description("API 문서 설명")
                .version("1.8")
                .contact(new Contact("관리자", "주소", "이메일"))
                .build();
    }

    private ApiKey getApiKey() {

        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext getSecurityContext() {

        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {

        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
