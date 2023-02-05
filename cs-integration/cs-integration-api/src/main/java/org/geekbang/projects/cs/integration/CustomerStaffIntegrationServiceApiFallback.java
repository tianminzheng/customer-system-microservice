package org.geekbang.projects.cs.integration;

import org.geekbang.projects.cs.integration.dto.OutsourcingSystemDTO;
import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;

import java.util.List;

public class CustomerStaffIntegrationServiceApiFallback implements CustomerStaffIntegrationServiceApi {

    @Override
    public List<PlatformCustomerStaff> fetchCustomerStaffs(OutsourcingSystemDTO outsourcingSystemDTO) {
        return null;
    }
}
