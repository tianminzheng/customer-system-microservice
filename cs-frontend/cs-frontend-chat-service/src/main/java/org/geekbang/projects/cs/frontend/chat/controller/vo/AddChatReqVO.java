package org.geekbang.projects.cs.frontend.chat.controller.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddChatReqVO {

    private Long userId;

    private Long staffId;

    private String inquire;

    private String ticketNo;
}
