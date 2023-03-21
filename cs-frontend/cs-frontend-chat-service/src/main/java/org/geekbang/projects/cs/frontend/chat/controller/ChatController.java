package org.geekbang.projects.cs.frontend.chat.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.geekbang.projects.cs.frontend.chat.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.frontend.chat.entity.ChatRecord;
import org.geekbang.projects.cs.frontend.chat.fallback.ChatFallback;
import org.geekbang.projects.cs.frontend.chat.service.IChatRecordService;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private IChatRecordService chatRecordService;

    int count = 0;

    @GetMapping(value = "/")
    @SentinelResource(value = "getChats",
            fallback = "getChatsFallback",
            fallbackClass = ChatFallback.class
    )
    Result<List<ChatRecord>> getChats() {

        count ++ ;
        if(count % 3 == 0){
            //模拟异常
            throw new RuntimeException("请求取余数为0");
        }

        List<ChatRecord> chatRecords = new ArrayList<>();
        chatRecords.add(buildChatRecord(1L));
        chatRecords.add(buildChatRecord(2L));
        return Result.success(chatRecords);
    }

    @GetMapping(value = "/{id}")
    @SentinelResource(value = "getChatById",
            fallback = "getChatByIdFallback",
            fallbackClass = ChatFallback.class
    )
    Result<ChatRecord> getChatById(@PathVariable("id") Long id) {

        if(id == 1){
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("id：" + id + ", 时间为：" + new Date().getTime());

        return Result.success(buildChatRecord(id));
    }

    @PostMapping(value = "/")
    Result<Boolean> insertChat(@RequestBody AddChatReqVO addChatReqVO) {

        chatRecordService.insertChat(addChatReqVO);
        return Result.success(true);
    }

    @GetMapping(value = "/switch")
    Result<Boolean> chatSwitch() {

        return Result.success(true);
    }

    private ChatRecord buildChatRecord(Long id) {
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setId(id);
        return chatRecord;
    }
}
