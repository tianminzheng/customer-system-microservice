package org.geekbang.projects.cs.frontend.ticket.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.geekbang.projects.cs.frontend.ticket.controller.vo.AddCustomerTicketReqVO;
import org.geekbang.projects.cs.frontend.ticket.converter.CustomerTicketConverter;
import org.geekbang.projects.cs.frontend.ticket.entity.CustomerTicket;
import org.geekbang.projects.cs.frontend.ticket.event.TicketGeneratedEvent;
import org.geekbang.projects.cs.frontend.ticket.mapper.CustomerTicketMapper;
import org.geekbang.projects.cs.frontend.ticket.mapper.TxRecordMapper;
import org.geekbang.projects.cs.frontend.ticket.service.ICustomerTicketService;
import org.geekbang.projects.cs.infrastructure.id.DistributedId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 * 客服工单表 服务实现类
 * </p>
 */
@Service
public class CustomerTicketServiceImpl extends ServiceImpl<CustomerTicketMapper, CustomerTicket> implements ICustomerTicketService {

    @Autowired
    TxRecordMapper txRecordMapper;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Override
    public void generateTicket(AddCustomerTicketReqVO addCustomerTicketReqVO) {

        //从VO中创建TicketGeneratedEvent
        TicketGeneratedEvent ticketGeneratedEvent = createTicketGeneratedEvent(addCustomerTicketReqVO);

        //将Event转化为JSON对象
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("ticketGeneratedEvent",ticketGeneratedEvent);
        String jsonString = jsonObject.toJSONString();

        //生成消息对象
        Message<String> message = MessageBuilder.withPayload(jsonString).build();

        //发送事务消息
        rocketMQTemplate.sendMessageInTransaction("producer_group_ticket","topic_ticket",message,null);
    }

    @Override
    @Transactional
    public void doGenerateTicket(TicketGeneratedEvent ticketGeneratedEvent) {

        //幂等判断
        if(Objects.nonNull(txRecordMapper.findTxRecordByTxNo(ticketGeneratedEvent.getTxNo()))){
            return ;
        }

        //插入工单
        CustomerTicket customerTicket = CustomerTicketConverter.INSTANCE.convertEvent(ticketGeneratedEvent);
        customerTicket.setStatus(1);
        save(customerTicket);

        //添加事务日志
        txRecordMapper.addTxRecord(ticketGeneratedEvent.getTxNo());

        //故意创建一个异常
        if(ticketGeneratedEvent.getUserId() == 100){
            throw new RuntimeException("人为制造异常");
        }
    }


    private TicketGeneratedEvent createTicketGeneratedEvent(AddCustomerTicketReqVO addCustomerTicketReqVO) {
        TicketGeneratedEvent ticketGeneratedEvent = new TicketGeneratedEvent();

        //创建一个全局事务Id
        String txNo = "TX-" + DistributedId.getInstance().getFastSimpleUUID();
        ticketGeneratedEvent.setTxNo(txNo);

        //生成全局工单编号，和聊天记录进行关联
        String ticketNo = "TICKET-" + DistributedId.getInstance().getFastSimpleUUID();
        ticketGeneratedEvent.setTicketNo(ticketNo);

        ticketGeneratedEvent.setInquire(addCustomerTicketReqVO.getInquire());
        ticketGeneratedEvent.setStaffId(addCustomerTicketReqVO.getStaffId());
        ticketGeneratedEvent.setUserId(addCustomerTicketReqVO.getUserId());

        return ticketGeneratedEvent;
    }
}
