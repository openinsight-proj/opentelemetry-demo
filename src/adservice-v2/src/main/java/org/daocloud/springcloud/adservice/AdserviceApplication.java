package org.daocloud.springcloud.adservice;

import org.daocloud.springcloud.adservice.init.SentinelClusterClientInitFunc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
public class AdserviceApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(AdserviceApplication.class, args);
    }

    @PostConstruct
    public void initSentinelClusterFlow() throws Exception{
        String sentinel = System.getProperty("spring.cloud.sentinel.enabled");
        if("true".equals(sentinel)){
            new SentinelClusterClientInitFunc().init();
        }
    }
}

