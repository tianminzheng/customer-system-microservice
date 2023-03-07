package org.geekbang.projects.cs.frontend.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.geekbang.projects.cs.frontend.ticket.controller.vo.AddTicketReqVO;
import org.geekbang.projects.cs.frontend.ticket.entity.CustomerTicket;
import org.geekbang.projects.cs.infrastructure.exception.BizException;
import org.geekbang.projects.cs.infrastructure.tcc.TccRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 客服工单表 服务类
 * </p>
 */
public interface ICustomerTicketService extends IService<CustomerTicket> {

    @Transactional(rollbackFor = Throwable.class)
    void insertTicket(TccRequest<AddTicketReqVO> tccRequest) throws BizException;

    @Transactional(rollbackFor = Throwable.class)
    void updateTicketSuccessStatus(TccRequest<String> ticketNo);

    @Transactional(rollbackFor = Throwable.class)
    void updateTicketFailStatus(TccRequest<String> ticketNo);
}
