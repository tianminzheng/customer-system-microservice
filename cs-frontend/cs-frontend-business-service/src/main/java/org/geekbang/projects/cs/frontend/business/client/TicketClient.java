package org.geekbang.projects.cs.frontend.business.client;

import org.geekbang.projects.cs.frontend.business.controller.vo.AddTicketReqVO;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "ticket-service")
public interface TicketClient {

    @RequestMapping(value = "/customerTickets/",method = RequestMethod.POST)
    Result<Boolean> insertTicket(@RequestBody AddTicketReqVO addTicketReqVO);
}
