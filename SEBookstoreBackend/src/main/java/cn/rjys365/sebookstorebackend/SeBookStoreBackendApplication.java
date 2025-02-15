package cn.rjys365.sebookstorebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SeBookStoreBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeBookStoreBackendApplication.class, args);
	}

}
