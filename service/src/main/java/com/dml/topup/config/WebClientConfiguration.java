package com.dml.topup.config;

import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * @author Ismael Sadeghi
 */
@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient restClient() {

        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(client ->
                        client.doOnConnected(conn -> conn
                                .addHandlerLast(new ReadTimeoutHandler(20))
                                .addHandlerLast(new WriteTimeoutHandler(20))));

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}