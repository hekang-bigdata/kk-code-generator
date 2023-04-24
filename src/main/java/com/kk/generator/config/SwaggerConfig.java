package com.kk.generator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @ProjectName: hyspgxptkf
 * @Package: com.vdata.cloud.hyspgxptkf.config
 * @ClassName: SwaggerConfig
 * @Author: HK
 * @Description:
 * @Date: 2023/4/4 10:15
 * @Version: 1.0
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vdata.cloud.hyspgxptkf.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot API")
                .description("Spring Boot API文档")
                .version("1.0.0")
                .build();
    }
}
