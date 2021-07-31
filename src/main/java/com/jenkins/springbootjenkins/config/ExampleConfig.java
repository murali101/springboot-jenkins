package com.jenkins.springbootjenkins.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.hello")
public class ExampleConfig {
    private String data;
}
