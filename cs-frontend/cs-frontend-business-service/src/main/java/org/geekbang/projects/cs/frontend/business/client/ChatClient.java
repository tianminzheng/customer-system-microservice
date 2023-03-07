package org.geekbang.projects.cs.frontend.business.client;

import org.geekbang.projects.cs.frontend.business.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "chat-service")
public interface ChatClient {

    @RequestMapping(value = "/chatRecords/",method = RequestMethod.POST)
    Result<Boolean> insertChat(@RequestBody AddChatReqVO addChatReqVO);
}
