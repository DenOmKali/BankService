package org.geekhub.denis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Apilat Denis
 * Date :02.05.2023
 * Time :14:47
 * Project Name :gh-hw-denis-apilat
 */

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/exchange/**");
        registry.addMapping("/news/**");
        registry.addMapping("/user/**");
        registry.addMapping("/card/**");
        registry.addMapping("/transaction/**");
        registry.addMapping("file/**");
    }
}
