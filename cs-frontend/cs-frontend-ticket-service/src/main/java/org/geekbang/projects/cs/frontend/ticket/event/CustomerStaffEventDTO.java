package org.geekbang.projects.cs.frontend.ticket.event;

import lombok.Data;

@Data
public class CustomerStaffEventDTO {

    private Long id;
    private String staffName;
    private Long accountId;
    private String phone;
}
