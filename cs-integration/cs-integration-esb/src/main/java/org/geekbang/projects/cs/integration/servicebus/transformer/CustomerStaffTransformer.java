package org.geekbang.projects.cs.integration.servicebus.transformer;

import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;

import java.util.List;

public interface CustomerStaffTransformer<T> {

    List<PlatformCustomerStaff> transformCustomerStaffs(List<T> customerStaffs);
}
