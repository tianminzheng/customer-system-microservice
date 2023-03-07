package org.geekbang.projects.cs.frontend.business.controller.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddTicketReqVO {

    private String ticketNo;

    private Long userId;

    private Long staffId;

    private String inquire;
}
