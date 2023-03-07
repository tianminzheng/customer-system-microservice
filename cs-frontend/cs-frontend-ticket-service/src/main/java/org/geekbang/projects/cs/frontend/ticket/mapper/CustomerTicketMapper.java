package org.geekbang.projects.cs.frontend.ticket.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.geekbang.projects.cs.frontend.ticket.entity.CustomerTicket;

public interface CustomerTicketMapper extends BaseMapper<CustomerTicket> {

    default void updateCustomerTicketTccStatus(String ticketNo, Integer tccStatus) {
        LambdaQueryWrapper<CustomerTicket> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(CustomerTicket::getTicketNo, ticketNo);
        CustomerTicket ticket = this.selectOne(queryWrapper);
        ticket.setTccStatus(tccStatus);
        this.updateById(ticket);
    }
}
