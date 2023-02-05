package org.geekbang.projects.cs.integration.servicebus.transformer.beijing;

import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;
import org.geekbang.projects.cs.integration.servicebus.router.beijing.dto.BeijingCustomerStaff;
import org.geekbang.projects.cs.integration.servicebus.transformer.CustomerStaffTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeijingCustomerStaffTransformer implements CustomerStaffTransformer<BeijingCustomerStaff> {

    @Override
    public List<PlatformCustomerStaff> transformCustomerStaffs(List<BeijingCustomerStaff> customerStaffs) {
        return null;
    }
}
