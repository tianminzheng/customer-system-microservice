package org.geekbang.projects.cs.frontend.ticket.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketGeneratedEvent implements Serializable {

    /**
     * 工单编号
     */
    private String ticketNo;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 客服人员Id
     */
    private Long staffId;

    /**
     * 工单咨询内容
     */
    private String inquire;

    /**
     * 事务号
     */
    private String txNo;
}
