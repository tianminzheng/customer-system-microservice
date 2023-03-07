package org.geekbang.projects.cs.frontend.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.geekbang.projects.cs.frontend.chat.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.frontend.chat.converter.ChatRecordConverter;
import org.geekbang.projects.cs.frontend.chat.entity.ChatRecord;
import org.geekbang.projects.cs.frontend.chat.mapper.ChatRecordMapper;
import org.geekbang.projects.cs.frontend.chat.service.IChatRecordService;
import org.geekbang.projects.cs.infrastructure.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 聊天记录主表 服务实现类
 * </p>
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements IChatRecordService {
    @Autowired
    ChatRecordMapper chatRecordMapper;

    @Override
    @Transactional
    public void insertChat(AddChatReqVO addChatReqVO) throws BizException {

        ChatRecord chatRecord = ChatRecordConverter.INSTANCE.convertVO(addChatReqVO);
        chatRecordMapper.insert(chatRecord);
    }
}
