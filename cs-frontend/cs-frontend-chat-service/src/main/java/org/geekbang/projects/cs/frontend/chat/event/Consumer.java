package org.geekbang.projects.cs.frontend.chat.event;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.geekbang.projects.cs.frontend.chat.converter.ChatRecordConverter;
import org.geekbang.projects.cs.frontend.chat.entity.ChatRecord;
import org.geekbang.projects.cs.frontend.chat.service.IChatRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer_group_ticket",topic = "topic_ticket")
public class Consumer implements RocketMQListener<String> {

    @Autowired
    IChatRecordService chatRecordService;

    //接收消息
    @Override
    public void onMessage(String message) {
        log.info("开始消费消息:{}",message);

        //解析消息
        JSONObject jsonObject = JSONObject.parseObject(message);
        String ticketGeneratedEventString = jsonObject.getString("ticketGeneratedEvent");

        //转成TicketGeneratedEvent
        TicketGeneratedEvent ticketGeneratedEvent = JSONObject.parseObject(ticketGeneratedEventString, TicketGeneratedEvent.class);

        //添加本地聊天记录
        chatRecordService.generateChatRecord(ticketGeneratedEvent);

    }
}
