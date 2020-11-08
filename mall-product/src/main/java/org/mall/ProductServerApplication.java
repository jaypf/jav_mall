package org.mall;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ProductServerApplication
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/9/24 16:11
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
//开启断路器功能
@EnableCircuitBreaker
//@EnableFeignClients(basePackages = {"com.demo.org.mall.service.feign"})
//开启重试功能
//@EnableRetry
@MapperScan("org.mall.mapper")
public class ProductServerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(ProductServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================PRODUCT-SERVER START SUCCESS!!!=====================");
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectionRequestTimeout(2000);
//        factory.setReadTimeout(4000);
        return new RestTemplate();
    }
}
