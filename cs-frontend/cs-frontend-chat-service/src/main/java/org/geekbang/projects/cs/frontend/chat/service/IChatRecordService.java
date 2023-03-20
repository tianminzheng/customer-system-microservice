package org.geekbang.projects.cs.frontend.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.geekbang.projects.cs.frontend.chat.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.frontend.chat.entity.ChatRecord;
import org.geekbang.projects.cs.infrastructure.exception.BizException;

/**
 * <p>
 * 聊天记录主表 服务类
 * </p>
 */
public interface IChatRecordService extends IService<ChatRecord> {

    void insertChat(AddChatReqVO addChatReqVO) throws BizException;
}
