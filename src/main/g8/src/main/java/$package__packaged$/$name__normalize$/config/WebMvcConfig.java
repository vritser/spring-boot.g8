package $package$.$name;format="normalize"$.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import $package$.$name;format="normalize"$.core.annotation.AuthorizedResolver;

/**
 * WebMvcConfig
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public AuthorizedResolver authorizedResolver() {
        return new AuthorizedResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authorizedResolver());
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public AuthorizationInterceptor authorizationInterceptor() {
        return new AuthorizationInterceptor();
    }

    // @Override
    // public void addCorsMappings(CorsRegistry registry) {
    //     registry.addMapping("/**")
    //         .allowedOriginPatterns("*")
    //         .allowCredentials(true)
    //         .allowedHeaders("*")
    //         .allowedMethods("*")
    //         .maxAge(300);

    //     WebMvcConfigurer.super.addCorsMappings(registry);
    // }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor())
            .addPathPatterns("/**/*")
            .excludePathPatterns("/swagger-ui.html","/v2/api-docs/**", "/swagger-resources/**","/webjars/**")
            .order(1);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
