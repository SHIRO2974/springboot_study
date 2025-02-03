package com.korit.springboot_study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // 해당 패키지 안에 있는 모든 컨트롤러 Swagger 적용
                .apis(RequestHandlerSelectors.basePackage("com.korit.springboot_study.controller"))
                .paths(PathSelectors.any()) // 모든 주소 및 URL Swagger 적용
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {

        return new ApiInfoBuilder()
                .title("API 문서 제목")
                .description("API 문서 설명")
                .version("1.8")
                .contact(new Contact("관리자", "주소", "이메일"))
                .build();
    }
}
