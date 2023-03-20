package org.geekbang.projects.cs.frontend.chat.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.geekbang.projects.cs.frontend.chat.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.frontend.chat.service.IChatRecordService;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private IChatRecordService chatRecordService;

    @GetMapping(value = "/")
    @SentinelResource(value = "getChats", blockHandler = "handlerBlock")
    Result<Boolean> getChats() {

        return Result.success(true);
    }

    @GetMapping(value = "/{id}")
    Result<Boolean> getChatById(@PathVariable("id") Long id) {

        return Result.success(id);
    }

    @PostMapping(value = "/")
    Result<Boolean> insertChat(@RequestBody AddChatReqVO addChatReqVO) {

        chatRecordService.insertChat(addChatReqVO);
        return Result.success(true);
    }

    public String handlerBlock(BlockException e) {
        return "Chat限流了...";
    }
}
