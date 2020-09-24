package org.jay;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @ClassName AdminServerApplication
 * @Description TODO
 * @Author Jay.Jia
 * @Date 2020/9/23 19:11
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableAdminServer
public class AdminServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================ADMIN-SERVER START SUCCESS!!!=====================");
    }

}
