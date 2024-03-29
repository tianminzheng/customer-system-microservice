package org.geekbang.projects.cs.middleground.customer.converter;

import org.geekbang.projects.cs.middleground.customer.controller.vo.req.AddCustomerStaffReqVO;
import org.geekbang.projects.cs.middleground.customer.controller.vo.req.UpdateCustomerStaffReqVO;
import org.geekbang.projects.cs.middleground.customer.controller.vo.resp.CustomerStaffRespVO;
import org.geekbang.projects.cs.middleground.customer.entity.staff.CustomerStaff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerStaffConverter {

    CustomerStaffConverter INSTANCE = Mappers.getMapper(CustomerStaffConverter.class);

    //VO->Entity
    CustomerStaff convertCreateReq(AddCustomerStaffReqVO addCustomerStaffReqVO);
    CustomerStaff convertUpdateReq(UpdateCustomerStaffReqVO updateCustomerStaffReqVO);

    //Entity->VO
    CustomerStaffRespVO convertResp(CustomerStaff entity);
    List<CustomerStaffRespVO> convertListResp(List<CustomerStaff> entities);
}
