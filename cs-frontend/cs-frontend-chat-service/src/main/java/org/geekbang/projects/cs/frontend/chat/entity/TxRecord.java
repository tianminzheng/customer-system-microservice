package org.geekbang.projects.cs.frontend.chat.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 事务执行记录表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tx_record")
public class TxRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事务唯一编号
     */
    private String txNo;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
