package org.geekbang.projects.cs.integration.servicebus.filter.impl;

import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;
import org.geekbang.projects.cs.integration.servicebus.filter.AbstractCustomerStaffFilter;

import java.util.Objects;

public class CustomerStaffEmptyFilter extends AbstractCustomerStaffFilter {

    public PlatformCustomerStaff execute(PlatformCustomerStaff customerStaff) {
        if (Objects.isNull(customerStaff.getStaffName())) {
            return null;
        }

        if (getNext() != null) {
            return getNext().execute(customerStaff);
        }

        return customerStaff;
    }
}
