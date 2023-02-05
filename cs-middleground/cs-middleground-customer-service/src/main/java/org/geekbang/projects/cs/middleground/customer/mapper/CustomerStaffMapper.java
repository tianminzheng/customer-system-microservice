package org.geekbang.projects.cs.middleground.customer.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.geekbang.projects.cs.middleground.customer.entity.staff.CustomerStaff;

public interface CustomerStaffMapper extends BaseMapper<CustomerStaff> {

    //建议大家在Mapper层做数据访问操作的封装
    default CustomerStaff findCustomerStaffByPhoneNumber(String phoneNumber) {

        LambdaQueryWrapper<CustomerStaff> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerStaff::getPhone, phoneNumber);
        queryWrapper.eq(CustomerStaff::getIsDeleted, false);

        return selectOne(queryWrapper);
    }



}
