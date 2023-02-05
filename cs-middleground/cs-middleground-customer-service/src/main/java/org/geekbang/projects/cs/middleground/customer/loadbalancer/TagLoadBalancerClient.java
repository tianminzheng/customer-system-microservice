package org.geekbang.projects.cs.middleground.customer.loadbalancer;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.cloud.nacos.balancer.NacosBalancer;
import org.geekbang.projects.cs.infrastructure.tag.TagUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TagLoadBalancerClient implements ReactorServiceInstanceLoadBalancer {

    @Value("${tag}")
    private String tagValue;

    // 服务列表
    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    public TagLoadBalancerClient(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider) {
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier supplier = serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);

        //TODO：先利用写死的Tag进行测试，需要结合网关进行重构
        return supplier.get().next().map(list -> getInstanceResponse(list, tagValue));
    }

    private Response<ServiceInstance> getInstanceResponse(
            List<ServiceInstance> instances, String tagValue) {
        if (instances.isEmpty()) {
            return new EmptyResponse();
        }

        List<ServiceInstance> chooseInstances = filterList(instances, instance -> tagValue.equals(TagUtils.getTag(instance)));
        if (CollUtil.isEmpty(chooseInstances)) {
            System.out.println("没有满足tag:" + tagValue + "的服务实例列表，直接使用所有服务实例列表");
            chooseInstances = instances;
        }

//        // 随机算法
//        int size = instances.size();
//        Random random = new Random();
//        ServiceInstance instance = instances.get(random.nextInt(size));
//        return new DefaultResponse(instance);

        //直接使用Nacos提供的随机+权重算法获取实例列表
        return new DefaultResponse(NacosBalancer.getHostByRandomWeight3(chooseInstances));
    }

    public static <T> List<T> filterList(Collection<T> from, Predicate<T> predicate) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }

        return from.stream().filter(predicate).collect(Collectors.toList());
    }
}