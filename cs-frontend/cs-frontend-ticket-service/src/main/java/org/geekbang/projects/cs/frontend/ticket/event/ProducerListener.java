package org.geekbang.projects.cs.frontend.ticket.event;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.geekbang.projects.cs.frontend.ticket.mapper.TxRecordMapper;
import org.geekbang.projects.cs.frontend.ticket.service.ICustomerTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "producer_group_ticket")
public class ProducerListener implements RocketMQLocalTransactionListener {

    @Autowired
    ICustomerTicketService customerTicketService;

    @Autowired
    TxRecordMapper txRecordMapper;

    //事务消息发送后的回调方法，当消息发送给mq成功，此方法被回调
    @Override
    @Transactional
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {

        try {
            //解析消息，转成Event对象
            TicketGeneratedEvent ticketGeneratedEvent = convertEvent(message);

            //执行本地事务
            customerTicketService.doGenerateTicket(ticketGeneratedEvent);

            //当返回RocketMQLocalTransactionState.COMMIT，自动向MQ发送commit消息，MQ将消息的状态改为可消费
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            //如果本地事务执行失败，就将消息设置为回滚状态
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    //事务状态回查
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        //解析消息，转成Event对象
        TicketGeneratedEvent ticketGeneratedEvent = convertEvent(message);

        //根据事务Id判断是否存在已执行的事务
        Boolean isTxNoExisted = Objects.nonNull(txRecordMapper.findTxRecordByTxNo(ticketGeneratedEvent.getTxNo()));

        //如果事务已执行则返回COMMIT，反之返回UNKNOWN状态
        if(isTxNoExisted){
            return RocketMQLocalTransactionState.COMMIT;
        }else{
            return RocketMQLocalTransactionState.UNKNOWN;
        }
    }

    private TicketGeneratedEvent convertEvent(Message message) {
        String messageString = new String((byte[]) message.getPayload());
        JSONObject jsonObject = JSONObject.parseObject(messageString);
        String ticketGeneratedEventString = jsonObject.getString("ticketGeneratedEvent");
        TicketGeneratedEvent ticketGeneratedEvent = JSONObject.parseObject(ticketGeneratedEventString, TicketGeneratedEvent.class);

        return ticketGeneratedEvent;
    }
}
