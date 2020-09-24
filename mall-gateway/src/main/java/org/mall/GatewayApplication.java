package org.mall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @ClassName GatewayApplication
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/9/24 11:47
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class GatewayApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================API-GATE-WAY START SUCCESS!!!=====================");
    }
}
