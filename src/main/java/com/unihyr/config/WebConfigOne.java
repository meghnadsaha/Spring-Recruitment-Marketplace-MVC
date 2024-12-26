package com.unihyr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// Tiles imports
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@EnableWebMvc
public class WebConfigOne implements WebMvcConfigurer {

    /**
     * Replaces multiple <mvc:resources> lines in XML
     * for serving static content from classpath or /static folder.
     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/css/**")
//                .addResourceLocations("classpath:/static/css/", "/WEB-INF/view/jsp/css/");
//        registry.addResourceHandler("/images/**")
//                .addResourceLocations("classpath:/static/images/", "/WEB-INF/view/jsp/images/");
//        registry.addResourceHandler("/js/**")
//                .addResourceLocations("classpath:/static/js/", "/WEB-INF/view/jsp/js/");
//        registry.addResourceHandler("/fonts/**")
//                .addResourceLocations("classpath:/static/fonts/", "/WEB-INF/view/jsp/fonts/");
//        registry.addResourceHandler("/ckeditor/**")
//                .addResourceLocations("classpath:/static/ckeditor/", "/WEB-INF/view/jsp/ckeditor/");
//    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/", "file:/WEB-INF/view/jsp/css/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/", "file:/WEB-INF/view/jsp/images/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/", "file:/WEB-INF/view/jsp/js/");
        registry.addResourceHandler("/fonts/**")
                .addResourceLocations("classpath:/static/fonts/", "file:/WEB-INF/view/jsp/fonts/");
    }



    /**
     * Tiles configuration to replace:
     * <bean id="tilesConfigurer" class="...TilesConfigurer">
     */
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(

                "/WEB-INF/tiles/tiles.xml",
                "/WEB-INF/view/jsp/admin/tiles.xml",
                "/WEB-INF/view/jsp/**/tiles.xml"
        );
        // If you need refreshable definitions, etc., set more properties here.
        configurer.setCheckRefresh(true); // Enables refreshing for changes in definitions
        return configurer;
    }

    /**
     * Replaces <bean id="viewResolver" class="UrlBasedViewResolver"> for TilesView
     */
    public UrlBasedViewResolver viewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }

    /**
     * If you want these beans to be recognized by Spring, you can
     * explicitly declare them as @Beans in the config class.
     */


    @Override
    public void addViewControllers( ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/accessDenied").setViewName("accessDenied");
    }

    @Bean
    public TilesConfigurer tilesConfigurerBean() {
//        TilesConfigurer tilesConfigurer = new TilesConfigurer();
//        tilesConfigurer.setDefinitions("/WEB-INF/tiles/tiles.xml");
//        tilesConfigurer.setCheckRefresh(true);
//        return tilesConfigurer;

        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(

                "/WEB-INF/tiles/tiles.xml",
                "/WEB-INF/view/jsp/admin/tiles.xml",
                "/WEB-INF/view/jsp/**/tiles.xml"
        );
        // If you need refreshable definitions, etc., set more properties here.
        configurer.setCheckRefresh(true); // Enables refreshing for changes in definitions
        return configurer;
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        TilesViewResolver viewResolver = new TilesViewResolver();
        viewResolver.setOrder(1);
        return viewResolver;
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/css/**")
                        .addResourceLocations("/WEB-INF/view/jsp/css/");
                registry.addResourceHandler("/images/**")
                        .addResourceLocations("/WEB-INF/view/jsp/images/");
                registry.addResourceHandler("/js/**")
                        .addResourceLocations("/WEB-INF/view/jsp/js/");
                registry.addResourceHandler("/fonts/**")
                        .addResourceLocations("/WEB-INF/view/jsp/fonts/");
                registry.addResourceHandler("/ckeditor/**")
                        .addResourceLocations("/WEB-INF/view/jsp/ckeditor/");
            }
        };
    }

}
