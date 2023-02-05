package org.geekbang.projects.cs.integration.servicebus.filter;


import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;

public interface CustomerStaffFilter {

    PlatformCustomerStaff execute(PlatformCustomerStaff customerStaff);

    void setNext(CustomerStaffFilter filter);

    CustomerStaffFilter getNext();

    CustomerStaffFilter getLast();
}
