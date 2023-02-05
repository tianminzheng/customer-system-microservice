package org.geekbang.projects.cs.integration;


import org.geekbang.projects.cs.integration.dto.OutsourcingSystemDTO;
import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;
import org.geekbang.projects.cs.infrastructure.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static org.geekbang.projects.cs.integration.ApiConstants.PREFIX;
import static org.geekbang.projects.cs.integration.ApiConstants.SERVICE_NAME;

@FeignClient(value = SERVICE_NAME, path = PREFIX + "/staffs", fallbackFactory = CustomerStaffIntegrationServiceApiFallback.class,
        configuration = FeignConfiguration.class)
public interface CustomerStaffIntegrationServiceApi {

    @RequestMapping(value="/", method = RequestMethod.POST)
    List<PlatformCustomerStaff> fetchCustomerStaffs(@RequestBody OutsourcingSystemDTO outsourcingSystemDTO);

}
