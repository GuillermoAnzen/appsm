package com.banamex.nearshore.appsms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("com.banamex.nearshore.*")
public class NearshoreAppsmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NearshoreAppsmsApplication.class, args);
	}
}
