package org.geekbang.projects.cs.frontend.ticket.converter;

import org.geekbang.projects.cs.frontend.ticket.controller.vo.AddTicketReqVO;
import org.geekbang.projects.cs.frontend.ticket.entity.CustomerTicket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CustomerTicketConverter {
    CustomerTicketConverter INSTANCE = Mappers.getMapper(CustomerTicketConverter.class);

    //VO->Entity
    CustomerTicket convertVO(AddTicketReqVO event);
}
