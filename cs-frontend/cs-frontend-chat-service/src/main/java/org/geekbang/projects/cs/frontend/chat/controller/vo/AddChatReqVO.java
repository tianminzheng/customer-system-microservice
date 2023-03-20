package org.geekbang.projects.cs.frontend.chat.controller.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AddChatReqVO implements Serializable {

    private Long userId;

    private Long staffId;

    private String inquire;

    private String ticketNo;
}
