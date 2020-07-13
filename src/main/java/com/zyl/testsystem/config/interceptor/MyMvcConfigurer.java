package com.zyl.testsystem.config.interceptor;

import com.zyl.testsystem.config.component.MyLocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jiangtingfeng
 * @description 拦截器
 * @date 2020/6/20/020
 */
@Configuration
public class MyMvcConfigurer implements WebMvcConfigurer {
    private final String dir= "50264d0499154aeeb4ec839de43b0efe";
    @Bean
    public WebMvcConfigurer  webMvcConfigurer() {
        WebMvcConfigurer  webMvcConfigurer = new WebMvcConfigurer () {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/excel.html").setViewName("excel");
                registry.addViewController("/ZJU/"+dir+"/index.html").setViewName("login");
                registry.addViewController("/relaxing.html").setViewName("relaxing");
                registry.addViewController("/testPrepare.html").setViewName("testPrepare");
                registry.addViewController("/testStage.html").setViewName("testStage");
                registry.addViewController("/end.html").setViewName("end");
            }
        };
        return webMvcConfigurer;
    }
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocalResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyLoginInterceptor()).addPathPatterns("/test_system/api/v1/**","/relaxing.html","/testPrepare.html","/testStage.html","/studyStage.html","/html/**")
                .excludePathPatterns("/ZJU/"+dir+"/index.html","index.html","/doc.html","/test_system/api/v1/user/login","/excel/**","/excel.html");

    }
}
