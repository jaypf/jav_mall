package org.mall.pay;

import lombok.extern.slf4j.Slf4j;
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
 * @ClassName org.mall.pay.PayServerApplication
 * @Description TODO
 * @Author Jay
 * @Date 2020/11/15 3:23
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
//开启断路器功能
@EnableCircuitBreaker
//@EnableFeignClients(basePackages = {"org.mall.service.feign"})
//开启重试功能
//@EnableRetry
//@MapperScan("com.demo.mapper")
public class PayServerApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(PayServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================PAY-SERVER START SUCCESS!!!=====================");
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
