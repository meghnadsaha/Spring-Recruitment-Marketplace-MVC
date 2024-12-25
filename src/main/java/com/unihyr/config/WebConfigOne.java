package com.unihyr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// Tiles imports
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@EnableWebMvc
public class WebConfigOne implements WebMvcConfigurer {

    /**
     * Replaces multiple <mvc:resources> lines in XML
     * for serving static content from classpath or /static folder.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // If you keep files in src/main/resources/static/css/ then:
        // registry.addResourceHandler("/css/**")
        //         .addResourceLocations("classpath:/static/css/");

        // But if you still place them under /WEB-INF/view/jsp/css/, you can do:
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

    /**
     * Tiles configuration to replace:
     * <bean id="tilesConfigurer" class="...TilesConfigurer">
     */
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(
                "/WEB-INF/tiles/tiles.xml",
                "/WEB-INF/view/jsp/**/tiles.xml"
        );
        // If you need refreshable definitions, etc., set more properties here.
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
    @org.springframework.context.annotation.Bean
    public TilesConfigurer tilesConfigurerBean() {
        return tilesConfigurer();
    }

    @org.springframework.context.annotation.Bean
    public UrlBasedViewResolver tilesViewResolver() {
        return viewResolver();
    }
}
