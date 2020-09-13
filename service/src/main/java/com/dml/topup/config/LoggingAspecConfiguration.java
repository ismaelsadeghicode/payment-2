package com.dml.topup.config;

import com.dml.topup.aop.LoggingAspec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Ismael Sadeghi
 */
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspecConfiguration {

    @Bean
    public LoggingAspec logAspect() {
        return new LoggingAspec();
    }
}
