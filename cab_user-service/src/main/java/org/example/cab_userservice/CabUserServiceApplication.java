package org.example.cab_userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CabUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CabUserServiceApplication.class, args);
    }

}
