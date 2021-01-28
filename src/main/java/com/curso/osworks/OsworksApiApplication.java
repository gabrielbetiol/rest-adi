package com.curso.osworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OsworksApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OsworksApiApplication.class, args);
    }

}
