package com.sgveteris.coincalculator.config;

import com.sgveteris.coincalculator.repository.redis.TickerMemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableFeignClients(basePackages = "com.sgveteris.coincalculator.service.blockchain")
@EnableJpaRepositories(basePackages = "com.sgveteris.coincalculator",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                value = TickerMemRepository.class))
@EnableJpaAuditing
@EnableRedisRepositories(basePackageClasses = TickerMemRepository.class)
@EnableCaching
public class CalculatorConfiguration {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}