package cn.rjys365.sebookstoresearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SeBookstoreSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeBookstoreSearchServiceApplication.class, args);
    }

}
