package org.geekbang.projects.cs.frontend.chat.controller;

import org.geekbang.projects.cs.frontend.chat.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.frontend.chat.service.IChatRecordService;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 客服工单表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/chatRecords")
public class ChatRecordController {

    @Autowired
    private IChatRecordService chatRecordService;

    @PostMapping(value = "/")
    Result<Boolean> insertChat(@RequestBody AddChatReqVO addChatReqVO) {

        chatRecordService.insertChat(addChatReqVO);
        return Result.success(true);

    }
}
