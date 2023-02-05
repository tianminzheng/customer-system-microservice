package org.geekbang.projects.cs.middleground.api.customer;

import org.geekbang.projects.cs.infrastructure.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.geekbang.projects.cs.middleground.api.ApiConstants.*;

@FeignClient(value = CUSTOMER_SERVICE_NAME, path = PREFIX + "/customerStaffs", fallbackFactory = CustomerStaffApiFallback.class,
        configuration = FeignConfiguration.class)
public interface CustomerStaffApi {

    @GetMapping(value = "/sync/{systemId}")
    void syncOutsourcingCustomerStaffsBySystemId(@PathVariable("systemId") Long systemId);
}
