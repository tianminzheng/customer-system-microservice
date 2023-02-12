package org.geekbang.projects.cs.frontend.ticket.event.stream;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.geekbang.projects.cs.frontend.ticket.entity.LocalCustomerStaff;
import org.geekbang.projects.cs.frontend.ticket.event.CustomerStaffChangedEvent;
import org.geekbang.projects.cs.frontend.ticket.event.CustomerStaffEventDTO;
import org.geekbang.projects.cs.frontend.ticket.service.ILocalCustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class CustomerStaffStreamConsumer {

    @Autowired
    private ILocalCustomerStaffService localCustomerStaffService;

    @Bean
    public Consumer<CustomerStaffChangedEvent> cluster() {
        return message -> {
            System.out.println("Received message : " + message);

            CustomerStaffEventDTO dto = message.getMessage();
            LocalCustomerStaff localCustomerStaff = new LocalCustomerStaff();

            convertLocalCustomerStaff(dto, localCustomerStaff);

            String operation = message.getOperation();
            if (operation.equals("CREATE")) {
                localCustomerStaffService.insertLocalCustomerStaff(localCustomerStaff);
            } else if (operation.equals("UPDATE")) {
                localCustomerStaffService.updateLocalCustomerStaff(localCustomerStaff);
            } else if (operation.equals("DELETE")) {
                localCustomerStaffService.deleteLocalCustomerStaff(localCustomerStaff);
            }
        };
    }

    private void convertLocalCustomerStaff(CustomerStaffEventDTO dto, LocalCustomerStaff localCustomerStaff) {
        localCustomerStaff.setStaffId(dto.getId());
        localCustomerStaff.setStaffName(dto.getStaffName());
        localCustomerStaff.setAccountId(dto.getAccountId());
        localCustomerStaff.setPhone(dto.getPhone());
    }
}
