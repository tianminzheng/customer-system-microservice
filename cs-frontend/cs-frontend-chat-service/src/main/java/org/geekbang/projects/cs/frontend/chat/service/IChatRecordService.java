package org.geekbang.projects.cs.frontend.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.geekbang.projects.cs.frontend.chat.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.frontend.chat.entity.ChatRecord;
import org.geekbang.projects.cs.infrastructure.exception.BizException;
import org.geekbang.projects.cs.infrastructure.tcc.TccRequest;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 聊天记录主表 服务类
 * </p>
 */
public interface IChatRecordService extends IService<ChatRecord> {

    @Transactional(rollbackFor = Throwable.class)
    void insertChat(TccRequest<AddChatReqVO> addChatReqVOTccRequest) throws BizException;

    @Transactional(rollbackFor = Throwable.class)
    void updateChatSuccessStatus(TccRequest<String> ticketNo);

    @Transactional(rollbackFor = Throwable.class)
    void updateChatFailStatus(TccRequest<String> ticketNo);
}
