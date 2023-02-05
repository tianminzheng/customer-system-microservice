package org.geekbang.projects.cs.integration.api;

import org.geekbang.projects.cs.integration.CustomerStaffIntegrationServiceApi;
import org.geekbang.projects.cs.integration.dto.OutsourcingSystemDTO;
import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;
import org.geekbang.projects.cs.integration.servicebus.endpoint.CustomerStaffEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/integration/staffs")
public class CustomerStaffIntegrationServiceApiImpl implements CustomerStaffIntegrationServiceApi {

    @Autowired
    CustomerStaffEndpoint customerStaffEndpoint;

    @Override
    @RequestMapping(value="/", method = RequestMethod.POST)
    public List<PlatformCustomerStaff> fetchCustomerStaffs(@RequestBody OutsourcingSystemDTO outsourcingSystemDTO) {

        return customerStaffEndpoint.fetchCustomerStaffs(outsourcingSystemDTO);
    }
}
