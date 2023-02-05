package org.geekbang.projects.cs.frontend.ticket.cache;

import org.geekbang.projects.cs.frontend.ticket.entity.LocalCustomerStaff;

public interface LocalCustomerStaffRedisRepository {

    void saveLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void updateLocalCustomerStaff(LocalCustomerStaff localCustomerStaff);

    void deleteLocalCustomerStaff(String staffId);

    LocalCustomerStaff findLocalCustomerStaffByStaffId(String staffId);

    void saveEmptyLocalCustomerStaff(String staffId);
}
