package org.geekbang.projects.cs.middleground.customer.loadbalancer;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CustomLoadBalancerConfig {

    @Bean
    public ReactorServiceInstanceLoadBalancer customLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        return new CustomRandomLoadBalancerClient(serviceInstanceListSupplierProvider);

//        return new TagLoadBalancerClient(serviceInstanceListSupplierProvider);
    }
}