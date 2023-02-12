package org.geekbang.projects.cs.middleground.customer.event.stream;

import org.apache.rocketmq.common.message.MessageConst;
import org.geekbang.projects.cs.middleground.customer.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.middleground.customer.event.CustomerStaffChangedEvent;
import org.geekbang.projects.cs.middleground.customer.event.CustomerStaffEventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerStaffChangedEventStreamDelayProducer {

    @Autowired
    private StreamBridge streamBridge;

    public static String DELAY_MESSAGE_OUTPUT = "delay-out-0";

    public void sendCustomerStaffChangedEvent(CustomerStaff customerStaff, String operation) {

        CustomerStaffEventDTO customerStaffEventDTO = new CustomerStaffEventDTO();
        customerStaffEventDTO.setId(customerStaff.getId());
        customerStaffEventDTO.setStaffName(customerStaff.getStaffName());
        customerStaffEventDTO.setAccountId(customerStaff.getAccountId());
        customerStaffEventDTO.setPhone(customerStaff.getPhone());

        CustomerStaffChangedEvent event = new CustomerStaffChangedEvent();
        event.setType("STAFF");
        event.setOperation(operation);
        event.setMessage(customerStaffEventDTO);

        Map<String, Object> headers = new HashMap<>();
        headers.put(MessageConst.PROPERTY_DELAY_TIME_LEVEL, 4);
        Message<CustomerStaffChangedEvent> message = new GenericMessage<>(event, headers);

        streamBridge.send(DELAY_MESSAGE_OUTPUT, message);
    }
}
