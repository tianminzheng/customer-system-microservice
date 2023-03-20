package org.geekbang.projects.cs.middleground.customer;

import org.geekbang.projects.cs.middleground.customer.loadbalancer.CustomLoadBalancerConfig;
import org.geekbang.projects.cs.middleground.customer.loadbalancer.RandomLoadBalancerConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@SpringBootApplication(scanBasePackages = {"org.geekbang.projects.cs"})
@MapperScan("org.geekbang.projects.cs.middleground.customer.mapper")
@LoadBalancerClient(name = "integration-service", configuration = CustomLoadBalancerConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
