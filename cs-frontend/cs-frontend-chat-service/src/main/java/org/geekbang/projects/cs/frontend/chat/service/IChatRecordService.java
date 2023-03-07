package org.geekbang.projects.cs.frontend.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.geekbang.projects.cs.frontend.chat.entity.ChatRecord;
import org.geekbang.projects.cs.frontend.chat.event.TicketGeneratedEvent;

/**
 * <p>
 * 聊天记录主表 服务类
 * </p>
 */
public interface IChatRecordService extends IService<ChatRecord> {

    //添加聊天记录
    void generateChatRecord(TicketGeneratedEvent ticketGeneratedEvent);
}
