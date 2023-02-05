package org.geekbang.projects.cs.middleground.task.feign;

import org.geekbang.projects.cs.infrastructure.config.FeignConfiguration;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.geekbang.projects.cs.middleground.task.feign.ApiConstants.PREFIX;
import static org.geekbang.projects.cs.middleground.task.feign.ApiConstants.SERVICE_NAME;

@FeignClient(value = SERVICE_NAME, path = PREFIX + "/sync", fallbackFactory = CustomerStaffSyncClientFallback.class,
        configuration = FeignConfiguration.class)
public interface CustomerStaffSyncClient {

    @RequestMapping(value="/{systemId}", method = RequestMethod.GET)
    Result<Boolean> syncOutsourcingCustomerStaffsBySystemId(@PathVariable("systemId") Long systemId);

}
