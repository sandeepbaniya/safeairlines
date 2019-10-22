package edu.mum.cs.cs452.safeairlines.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebMvc
@Configuration
public class WebApplicationContextConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        if (!registry.hasMappingForPattern("/css/**")) {
            registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        }

        if (!registry.hasMappingForPattern("/js/**")) {
            registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        }

        if (!registry.hasMappingForPattern("/user/css/**")) {
            registry.addResourceHandler("/user/css/**").addResourceLocations("classpath:/static/js/");
        }

        if (!registry.hasMappingForPattern("/user/css/**")) {
            registry.addResourceHandler("/user/css/**").addResourceLocations("classpath:/static/js/");
        }

        if (!registry.hasMappingForPattern("/font/**")) {
            registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/fonts/");
        }

        if (!registry.hasMappingForPattern("/images/**")) {
            registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        }


    }


}
