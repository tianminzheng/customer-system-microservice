package org.geekbang.projects.cs.frontend.business.service;

import io.seata.spring.annotation.GlobalTransactional;
import org.geekbang.projects.cs.frontend.business.client.ChatClient;
import org.geekbang.projects.cs.frontend.business.client.TicketClient;
import org.geekbang.projects.cs.frontend.business.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.frontend.business.controller.vo.AddTicketReqVO;
import org.geekbang.projects.cs.infrastructure.id.DistributedId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private TicketClient ticketClient;

    @Autowired
    private ChatClient chatClient;

    @GlobalTransactional
    public void initializeCustomerTicket(Long userId, Long staffId, String inquire) {

        String ticketNo = DistributedId.getInstance().getFastSimpleUUID();
        AddTicketReqVO addTicketReqVO = new AddTicketReqVO()
                .setUserId(userId)
                .setStaffId(staffId)
                .setTicketNo(ticketNo)
                .setInquire(inquire);

        ticketClient.insertTicket(addTicketReqVO);

        AddChatReqVO addChatReqVO = new AddChatReqVO()
                .setUserId(userId)
                .setStaffId(staffId)
                .setTicketNo(ticketNo)
                .setInquire(inquire);

        chatClient.insertChat(addChatReqVO);

//        throw new RuntimeException("发生了异常，分布式事务需要回滚");
    }

}
