package org.geekbang.projects.cs.frontend.ticket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.geekbang.projects.cs.frontend.ticket.entity.LocalCustomerStaff;
import org.geekbang.projects.cs.frontend.ticket.mapper.LocalCustomerStaffMapper;
import org.geekbang.projects.cs.frontend.ticket.service.ILocalCustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocalCustomerStaffServiceImpl extends ServiceImpl<LocalCustomerStaffMapper, LocalCustomerStaff> implements ILocalCustomerStaffService {

    @Autowired
    LocalCustomerStaffMapper localCustomerStaffMapper;

    @Override
    @Transactional
    public void insertLocalCustomerStaff(LocalCustomerStaff localCustomerStaff) {

        localCustomerStaffMapper.insert(localCustomerStaff);
    }

    @Override
    public void updateLocalCustomerStaff(LocalCustomerStaff localCustomerStaff) {

        localCustomerStaffMapper.updateById(localCustomerStaff);
    }

    @Override
    public void deleteLocalCustomerStaff(LocalCustomerStaff localCustomerStaff) {

        localCustomerStaffMapper.deleteById(localCustomerStaff);
    }

    @Override
    public LocalCustomerStaff findLocalCustomerStaffByStaffId(Long staffId) {

        return localCustomerStaffMapper.findLocalCustomerStaffByStaffId(staffId);
    }
}
