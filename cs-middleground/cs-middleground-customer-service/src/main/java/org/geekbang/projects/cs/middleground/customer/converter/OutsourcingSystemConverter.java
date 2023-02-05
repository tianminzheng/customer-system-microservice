package org.geekbang.projects.cs.middleground.customer.converter;

import org.geekbang.projects.cs.middleground.customer.controller.vo.req.AddOutsourcingSystemReqVO;
import org.geekbang.projects.cs.middleground.customer.controller.vo.resp.OutsourcingSystemRespVO;
import org.geekbang.projects.cs.middleground.customer.entity.tenant.OutsourcingSystem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OutsourcingSystemConverter {

    OutsourcingSystemConverter INSTANCE = Mappers.getMapper(OutsourcingSystemConverter.class);

    //VO->Entity
    OutsourcingSystem convertCreateReq(AddOutsourcingSystemReqVO addOutsourcingSystemReqVO);

    //Entity->VO
    OutsourcingSystemRespVO convertResp(OutsourcingSystem entity);
    List<OutsourcingSystemRespVO> convertListResp(List<OutsourcingSystem> entities);
}
