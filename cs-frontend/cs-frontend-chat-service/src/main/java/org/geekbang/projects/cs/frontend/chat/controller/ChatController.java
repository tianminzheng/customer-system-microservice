package org.geekbang.projects.cs.frontend.chat.controller;

import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @GetMapping(value = "/")
    Result<Boolean> getChats() {

        return Result.success(true);
    }
}
