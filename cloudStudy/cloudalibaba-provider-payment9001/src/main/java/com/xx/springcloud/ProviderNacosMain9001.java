package com.xx.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderNacosMain9001 {
    public static void main(String[] args){
      SpringApplication.run(ProviderNacosMain9001.class, args);
    }
}
