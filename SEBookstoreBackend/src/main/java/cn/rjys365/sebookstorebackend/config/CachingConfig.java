package cn.rjys365.sebookstorebackend.config;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig implements CachingConfigurer {
    @Bean
    @Override
    public CustomCacheErrorHandler errorHandler() {
        return new CustomCacheErrorHandler();
    }
}
