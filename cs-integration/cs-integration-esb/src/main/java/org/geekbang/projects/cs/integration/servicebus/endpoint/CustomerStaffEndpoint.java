package org.geekbang.projects.cs.integration.servicebus.endpoint;


import org.geekbang.projects.cs.integration.dto.OutsourcingSystemDTO;
import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;

import java.util.List;

public interface CustomerStaffEndpoint {

    List<PlatformCustomerStaff> fetchCustomerStaffs(OutsourcingSystemDTO outsourcingSystem);
}
