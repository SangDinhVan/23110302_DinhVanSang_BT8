package sang.com.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {
    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(new Info()
                .title("X-mate Admin API")
                .description("CRUD API cho Category & Product")
                .version("v1"));
    }
}
