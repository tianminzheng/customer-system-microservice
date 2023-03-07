package org.geekbang.projects.cs.frontend.ticket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  事务记录表
 * @author scott lewis 2019-05-28
 */
@Data
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * xid
     */
    private String xid;

    /**
     * branch_id
     */
    private Long branchId;

    /**
     * args_json
     */
    private String data;

    /**
     * 1，初始化；2，已提交；3，已回滚
     */
    private Integer state;

    /**
     * gmt_create
     */
    private Date gmtCreate;

    /**
     * gmt_modified
     */
    private Date gmtModified;

    public Transaction() {
    }

}
