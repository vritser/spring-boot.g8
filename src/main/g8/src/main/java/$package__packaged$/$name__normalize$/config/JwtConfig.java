package $package$.$name;format="normalize"$.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * JwtConfig
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secret;
}
