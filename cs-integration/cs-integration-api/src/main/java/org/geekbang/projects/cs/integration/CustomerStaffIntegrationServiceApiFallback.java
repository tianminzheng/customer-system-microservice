package org.geekbang.projects.cs.integration;

import org.geekbang.projects.cs.integration.dto.OutsourcingSystemDTO;
import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerStaffIntegrationServiceApiFallback implements FallbackFactory<CustomerStaffIntegrationServiceApi> {

    @Override
    public CustomerStaffIntegrationServiceApi create(Throwable cause) {

        return new CustomerStaffIntegrationServiceApi() {
            @Override
            public List<PlatformCustomerStaff> fetchCustomerStaffs(OutsourcingSystemDTO outsourcingSystemDTO) {
                System.out.println("CustomerStaffIntegrationServiceApiFallback is called！");
                return null;
            }
        };
    }
}
