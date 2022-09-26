package com.example.security.config;

import com.samskivert.mustache.Mustache;
import org.hibernate.tuple.CreationTimestampGeneration;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        MustacheViewResolver resolver = new MustacheViewResolver();
        resolver.setCharset("UTF-8"); // 뷰는 인코딩 UTF-8 이고
        resolver.setContentType("text/html; charset=UTF-8"); // HTML파일이며 UTF-8이고
        resolver.setPrefix("classpath:/templates/"); // 클래스 패스는 탬플릿 밑이고
        resolver.setSuffix(".html"); // HTML도 실행한다.

        registry.viewResolver(resolver);
    }
}
