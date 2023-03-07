package org.geekbang.projects.cs.frontend.chat.converter;

import org.geekbang.projects.cs.frontend.chat.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.frontend.chat.entity.ChatRecord;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ChatRecordConverter {
    ChatRecordConverter INSTANCE = Mappers.getMapper(ChatRecordConverter.class);

    //Event->Entity
    ChatRecord convertVO(AddChatReqVO vo);
}
