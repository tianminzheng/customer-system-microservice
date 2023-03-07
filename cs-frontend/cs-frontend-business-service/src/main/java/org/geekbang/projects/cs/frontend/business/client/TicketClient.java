package org.geekbang.projects.cs.frontend.business.client;

import org.geekbang.projects.cs.frontend.business.controller.vo.AddTicketReqVO;
import org.geekbang.projects.cs.infrastructure.tcc.TccRequest;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "ticket-service")
public interface TicketClient {

    @RequestMapping(value = "/customerTickets/try",method = RequestMethod.POST)
    Result<Boolean> ticketTry(@RequestBody TccRequest<AddTicketReqVO> addTicketReqVO);

    @RequestMapping(value = "/customerTickets/confirm",method = RequestMethod.POST)
    Result<Boolean> ticketConfirm(@RequestBody TccRequest<String> ticketNo);

    @RequestMapping(value = "/customerTickets/cancel",method = RequestMethod.POST)
    Result<Boolean> ticketCancel(@RequestBody TccRequest<String> ticketNo);
}
