package org.geekbang.projects.cs.frontend.ticket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.geekbang.projects.cs.frontend.ticket.controller.vo.AddTicketReqVO;
import org.geekbang.projects.cs.frontend.ticket.converter.CustomerTicketConverter;
import org.geekbang.projects.cs.frontend.ticket.entity.CustomerTicket;
import org.geekbang.projects.cs.frontend.ticket.mapper.CustomerTicketMapper;
import org.geekbang.projects.cs.frontend.ticket.service.ICustomerTicketService;
import org.geekbang.projects.cs.infrastructure.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 客服工单表 服务实现类
 * </p>
 */
@Service
public class CustomerTicketServiceImpl extends ServiceImpl<CustomerTicketMapper, CustomerTicket> implements ICustomerTicketService {

    @Autowired
    CustomerTicketMapper customerTicketMapper;

    @Override
    @Transactional
    public void insertTicket(AddTicketReqVO addTicketReqVO) throws BizException {

        CustomerTicket customerTicket = CustomerTicketConverter.INSTANCE.convertVO(addTicketReqVO);
        customerTicketMapper.insert(customerTicket);
    }
}
