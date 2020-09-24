package org.jay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
//import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @ClassName DashboardServerApplication
 * @Description
 * 监控界面：http://localhost:9990/hystrix
 * 需要监控的端点（使用了hystrix组件的端点）：http://localhost:9990/actuator/hystrix.stream
 * http://localhost:9990/turbine.stream
 * @Author Jay.Jia
 * @Date 2020/9/24 11:01
 * @Version 1.0
 */
@Slf4j
@SpringBootApplication
//@EnableTurbine
@EnableDiscoveryClient
@EnableHystrixDashboard
public class DashboardServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DashboardServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("================DASHBOARD-SERVER START SUCCESS!!!=====================");
    }
}
