package org.geekbang.projects.cs.frontend.business.controller;

import org.geekbang.projects.cs.frontend.business.controller.vo.AddTicketReqVO;
import org.geekbang.projects.cs.frontend.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping(value = "/")
    public void generateTicket(@RequestBody AddTicketReqVO addCustomerTicketReqVO){

        businessService.initializeCustomerTicket(addCustomerTicketReqVO.getUserId(),
                addCustomerTicketReqVO.getStaffId(),
                addCustomerTicketReqVO.getInquire());
    }
}
