package org.geekbang.projects.cs.frontend.ticket.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.geekbang.projects.cs.frontend.ticket.controller.vo.AddCustomerTicketReqVO;
import org.geekbang.projects.cs.frontend.ticket.entity.CustomerTicket;
import org.geekbang.projects.cs.frontend.ticket.event.TicketGeneratedEvent;

/**
 * <p>
 * 客服工单表 服务类
 * </p>
 */
public interface ICustomerTicketService extends IService<CustomerTicket> {

    //添加工单记录
    void generateTicket(AddCustomerTicketReqVO addCustomerTicketReqVO);

    //执行工单生成操作
    void doGenerateTicket(TicketGeneratedEvent ticketGeneratedEvent);
}
