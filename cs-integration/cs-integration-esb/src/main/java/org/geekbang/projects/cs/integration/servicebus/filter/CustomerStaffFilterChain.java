package org.geekbang.projects.cs.integration.servicebus.filter;

import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;

public class CustomerStaffFilterChain {

    private CustomerStaffFilter chain;

    public void addFilter(CustomerStaffFilter filter) {
        if (chain == null) {
            chain = filter;
        } else {
            chain.getLast().setNext(filter);
        }
    }

    public PlatformCustomerStaff execute(PlatformCustomerStaff customerStaff) {
        if (chain != null) {
            return chain.execute(customerStaff);
        } else {
            return null;
        }
    }
}
