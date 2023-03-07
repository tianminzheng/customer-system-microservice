package org.geekbang.projects.cs.frontend.ticket.controller;

import org.geekbang.projects.cs.frontend.ticket.controller.vo.AddCustomerTicketReqVO;
import org.geekbang.projects.cs.frontend.ticket.service.ICustomerTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public void generateTicket(@RequestBody AddCustomerTicketReqVO addCustomerTicketReqVO){

        customerTicketService.generateTicket(addCustomerTicketReqVO);
    }
}
