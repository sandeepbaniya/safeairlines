//package edu.mum.cs.cs452.safeairlines.config;
//
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
//import java.util.Locale;
//
//
//@EnableWebMvc
//@Configuration
//public class WebApplicationContextConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//
//
//
//        if (!registry.hasMappingForPattern("/css/**")) {
//            registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
//        }
//
//        if (!registry.hasMappingForPattern("/js/**")) {
//            registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
//        }
//
//        if (!registry.hasMappingForPattern("/user/css/**")) {
//            registry.addResourceHandler("/user/css/**").addResourceLocations("classpath:/static/js/");
//        }
//
//        if (!registry.hasMappingForPattern("/user/css/**")) {
//            registry.addResourceHandler("/user/css/**").addResourceLocations("classpath:/static/js/");
//        }
//
//        if (!registry.hasMappingForPattern("/font/**")) {
//            registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/fonts/");
//        }
//
//        if (!registry.hasMappingForPattern("/images/**")) {
//            registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
//        }
//
//        if (!registry.hasMappingForPattern("/flight/images/**")) {
//            registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
//        }
//
//    }
//
//
////    @Override
////    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
////    }
//
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver slr = new SessionLocaleResolver();
//        slr.setDefaultLocale(Locale.US);
//        return slr;
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("lang");
//        return lci;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//
//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setBasenames("classpath:errorMessages", "classpath:messages");
//        messageSource.setDefaultEncoding("UTF-8");
//        return messageSource;
//    }
//    @Bean
//    public LocalValidatorFactoryBean validator() {
//        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
//        bean.setValidationMessageSource(messageSource());
//        return bean;
//    }
//
//
//
//}
