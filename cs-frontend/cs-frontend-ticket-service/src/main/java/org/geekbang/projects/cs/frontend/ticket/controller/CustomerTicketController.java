package org.geekbang.projects.cs.frontend.ticket.controller;

import org.geekbang.projects.cs.frontend.ticket.controller.vo.AddTicketReqVO;
import org.geekbang.projects.cs.frontend.ticket.service.ICustomerTicketService;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 客服工单表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/customerTickets")
public class CustomerTicketController {

    @Autowired
    private ICustomerTicketService customerTicketService;

    @PostMapping(value = "/")
    Result<Boolean> insertTicket(@RequestBody AddTicketReqVO addTicketReqVO) {

        customerTicketService.insertTicket(addTicketReqVO);
        return Result.success(true);
    }
}
