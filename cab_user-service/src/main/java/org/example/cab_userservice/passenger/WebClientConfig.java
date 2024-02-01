package org.example.cab_userservice.passenger;

import org.example.authservice.client.PassengerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient passengerWebClient() {
        return WebClient.builder()
                .baseUrl("http://cab_user-service")  // Replace with the correct service name
                .filter(filterFunction)
                .build();
    }

    @Bean
    public org.example.authservice.client.PassengerClient passengerClient() {
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builder()
                .build();

        return httpServiceProxyFactory.createClient(PassengerClient.class);
    }
}

