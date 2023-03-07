package org.geekbang.projects.cs.frontend.business.client;

import org.geekbang.projects.cs.frontend.business.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.infrastructure.tcc.TccRequest;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "chat-service")
public interface ChatClient {

    @RequestMapping(value = "/chatRecords/try",method = RequestMethod.POST)
    Result<Boolean> chatTry(@RequestBody TccRequest<AddChatReqVO> addChatReqVO);

    @RequestMapping(value = "/chatRecords/confirm",method = RequestMethod.POST)
    Result<Boolean> chatConfirm(@RequestBody TccRequest<String> ticketNo);

    @RequestMapping(value = "/chatRecords/cancel",method = RequestMethod.POST)
    Result<Boolean> chatCancel(@RequestBody TccRequest<String> ticketNo);
}
