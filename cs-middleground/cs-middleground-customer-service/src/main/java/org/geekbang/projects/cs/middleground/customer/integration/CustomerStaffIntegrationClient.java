package org.geekbang.projects.cs.middleground.customer.integration;

import org.geekbang.projects.cs.middleground.customer.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.middleground.customer.entity.tenant.OutsourcingSystem;
import org.geekbang.projects.cs.integration.CustomerStaffIntegrationServiceApi;
import org.geekbang.projects.cs.integration.dto.OutsourcingSystemDTO;
import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;
import org.geekbang.projects.cs.middleground.customer.entity.staff.CustomerStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerStaffIntegrationClient {

    @Autowired
    CustomerStaffIntegrationServiceApi customerStaffIntegrationServiceApi;

    public List<CustomerStaff> getCustomerStaffs(OutsourcingSystem outsourcingSystem) {

        OutsourcingSystemDTO outsourcingSystemDTO = CustomerStaffIntegrationConverter.INSTANCE.convertOutsourcingSystem(outsourcingSystem);

        List<PlatformCustomerStaff> platformCustomerStaffs = customerStaffIntegrationServiceApi.fetchCustomerStaffs(outsourcingSystemDTO);

        return CustomerStaffIntegrationConverter.INSTANCE.convertCustomerStaffListDTO(platformCustomerStaffs);
    }
}
