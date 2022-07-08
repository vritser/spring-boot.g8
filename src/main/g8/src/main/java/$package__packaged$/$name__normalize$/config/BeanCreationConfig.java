package $package$.$name;format="normalize"$.config;

import javax.validation.Validation;
import javax.validation.Validator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * BeanCreationConfig
 */
@Configuration
@EnableSwagger2
public class BeanCreationConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(3000);
        return factory;
    }

    @Bean
    public Validator validator() {
        var validatorFactory = Validation.byProvider(HibernateValidator.class)
            .configure()
            .failFast(true)
            .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("$package$.$name;format="normalize"$.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    // @Bean
    // public CorsFilter corsFilter() {
    //     final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     final CorsConfiguration config = new CorsConfiguration();
    //     config.setAllowCredentials(true);
    //     config.setAllowedOrigins(Arrays.asList("*"));
    //     config.setAllowedHeaders(Arrays.asList("*"));
    //     config.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
    //     config.setMaxAge(300L);

    //     source.registerCorsConfiguration("/**", config);
    //     return new CorsFilter(source);
    // }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("$name$ API Doc")
            .description("$name$ 接口文档")
            .version("1.0")
            .build();
    }
}
