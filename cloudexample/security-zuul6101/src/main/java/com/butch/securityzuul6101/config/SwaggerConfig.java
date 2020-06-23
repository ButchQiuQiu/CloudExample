package com.butch.securityzuul6101.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * swaggerConfig的配置类，主要配置Docket
 */
@Configuration
public class SwaggerConfig {
    @Bean
    @SuppressWarnings("all")
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        //作者信息
        docket.apiInfo(new ApiInfo("权限网关", "权限方面的接口", "1.0", "https://github.com/ButchQiuQiu",
                new Contact("ButchQiuQiu", "https://github.com/ButchQiuQiu/CloudExample", "133465202@qq.com"),
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList()))
                //apis为指定扫描的方式和位置
                    //basePackage为指定扫描的包,这样就可以过滤掉其他无关的接口.
                //paths()为过滤
                //groupName为分组,每个组都是独立的docket,可以注入多个docket只要分组不一样即可.
                .select().apis(RequestHandlerSelectors.basePackage("com.butch.securityzuul6101.controller")).build();

        return docket;
    }
}