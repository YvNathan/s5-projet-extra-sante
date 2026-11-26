package itu.s5.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration 
@ComponentScan(basePackages = {
    "itu.s5.service",
    "itu.s5.repository"
})
public class AppConfig {
    
}
