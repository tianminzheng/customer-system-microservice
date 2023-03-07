package org.geekbang.projects.cs.frontend.chat.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.geekbang.projects.cs.frontend.chat.entity.ChatRecord;

/**
 * <p>
 * 聊天记录主表 Mapper 接口
 * </p>
 */
public interface ChatRecordMapper extends BaseMapper<ChatRecord> {

    default void updateChatRecordTccStatus(String ticketNo, Integer tccStatus) {
        LambdaQueryWrapper<ChatRecord> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(ChatRecord::getTicketNo, ticketNo);

        ChatRecord chatRecord = this.selectOne(queryWrapper);
        chatRecord.setTccStatus(tccStatus);
        this.updateById(chatRecord);
    }
}
