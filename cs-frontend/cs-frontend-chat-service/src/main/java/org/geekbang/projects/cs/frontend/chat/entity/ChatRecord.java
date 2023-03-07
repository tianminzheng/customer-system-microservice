package org.geekbang.projects.cs.frontend.chat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 聊天记录主表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("chat_record")
public class ChatRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
     * 最新一条消息
     */
    private String lastMessage;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
