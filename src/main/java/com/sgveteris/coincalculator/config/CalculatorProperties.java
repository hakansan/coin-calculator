package com.sgveteris.coincalculator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "com.sgveteris.properties")
@Component
@Data
public class CalculatorProperties {

    private String blockchainApiBaseUrl;
}