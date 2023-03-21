package org.geekbang.projects.cs.frontend.chat.fallback;

import org.geekbang.projects.cs.frontend.chat.entity.ChatRecord;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class ChatFallback {

    public static Result<ChatRecord> getChatByIdFallback(@PathVariable("id") Long id, Throwable throwable) {

        System.out.println("【进入 getChatByIdFallback 方法】");

        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setId(-1L);
        return Result.success(chatRecord);
    }

    public static Result<ChatRecord> getChatsFallback(Throwable throwable) {

        System.out.println("【进入 getChatsFallback 方法】");

        List<ChatRecord> chatRecords = new ArrayList<>();
        ChatRecord chatRecord = new ChatRecord();
        chatRecord.setId(-1L);
        chatRecords.add(chatRecord);
        return Result.success(chatRecord);
    }
}
