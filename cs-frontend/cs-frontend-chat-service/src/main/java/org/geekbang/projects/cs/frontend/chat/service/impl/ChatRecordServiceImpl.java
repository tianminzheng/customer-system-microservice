package org.geekbang.projects.cs.frontend.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.geekbang.projects.cs.frontend.chat.converter.ChatRecordConverter;
import org.geekbang.projects.cs.frontend.chat.entity.ChatRecord;
import org.geekbang.projects.cs.frontend.chat.event.TicketGeneratedEvent;
import org.geekbang.projects.cs.frontend.chat.mapper.ChatRecordMapper;
import org.geekbang.projects.cs.frontend.chat.mapper.TxRecordMapper;
import org.geekbang.projects.cs.frontend.chat.service.IChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 聊天记录主表 服务实现类
 * </p>
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements IChatRecordService {

    @Autowired
    TxRecordMapper txRecordMapper;

    @Override
    @Transactional
    public void generateChatRecord(TicketGeneratedEvent ticketGeneratedEvent) {

        //幂等判断
        if(Objects.nonNull(txRecordMapper.findTxRecordByTxNo(ticketGeneratedEvent.getTxNo()))){
            return ;
        }

        //插入聊天记录
        ChatRecord chatRecord = ChatRecordConverter.INSTANCE.convertEvent(ticketGeneratedEvent);
        save(chatRecord);

        //添加事务日志
        txRecordMapper.addTxRecord(ticketGeneratedEvent.getTxNo());

        //故意创建一个异常
//        if(ticketGeneratedEvent.getUserId() == 100){
//            throw new RuntimeException("人为制造异常");
//        }
    }
}
