package com.zyl.testsystem.config.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Value;
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

/**
 * @Description:    Swagger2Config 配置功能模块
 * @Author:         xiaoliang.chen
 * @Date:     2019/8/21 上午11:45
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {

    @Value("${swagger2.enable}")
    private boolean enable;

    @Bean("心理测试接口")
    public Docket regulationApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("心理测试接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zyl.testsystem.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("浙江大学心理学学院", "http://www.psych.zju.edu.cn/", "pubaccount@psych.zju.edu.cn");
        return new ApiInfoBuilder()
                .title("心理测试")
                .description("心理测试相关接口")
                .termsOfServiceUrl("http://www.psych.zju.edu.cn/")
                .contact(contact)
                .version("0.1")
                .build();
    }

}