package suporte.techne.flightapp.infra.configuration;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import suporte.techne.flightapp.infra.security.RateLimitingFilter;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public Filter rateLimitingFilter() {
        return new RateLimitingFilter();
    }
    
}