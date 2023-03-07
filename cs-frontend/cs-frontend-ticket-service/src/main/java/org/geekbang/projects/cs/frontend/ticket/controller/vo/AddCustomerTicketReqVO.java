package org.geekbang.projects.cs.frontend.ticket.controller.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddCustomerTicketReqVO {

    private Long userId;

    private Long staffId;

    private String inquire;
}
