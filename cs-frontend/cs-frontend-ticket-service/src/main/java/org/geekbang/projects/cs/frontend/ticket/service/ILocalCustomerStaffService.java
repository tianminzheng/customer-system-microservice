package org.geekbang.projects.cs.frontend.ticket.service;

import org.geekbang.projects.cs.frontend.ticket.entity.LocalCustomerStaff;

public interface ILocalCustomerStaffService {

    void insertLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void updateLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void deleteLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    LocalCustomerStaff findLocalCustomerStaffByStaffId(Long staffId);
}
