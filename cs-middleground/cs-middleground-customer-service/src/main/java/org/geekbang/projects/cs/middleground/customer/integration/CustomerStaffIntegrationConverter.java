package org.geekbang.projects.cs.middleground.customer.integration;

import org.geekbang.projects.cs.middleground.customer.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.middleground.customer.entity.tenant.OutsourcingSystem;
import org.geekbang.projects.cs.integration.dto.OutsourcingSystemDTO;
import org.geekbang.projects.cs.integration.dto.PlatformCustomerStaff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerStaffIntegrationConverter {

    CustomerStaffIntegrationConverter INSTANCE = Mappers.getMapper(CustomerStaffIntegrationConverter.class);

    //Entity->DTO
    OutsourcingSystemDTO convertOutsourcingSystem(OutsourcingSystem entity);

    //DTO->Entity
    List<CustomerStaff> convertCustomerStaffListDTO(List<PlatformCustomerStaff> dtos);
}
