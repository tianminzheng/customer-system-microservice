package org.geekbang.projects.cs.middleground.customer.controller.webmvc;

import org.geekbang.projects.cs.infrastructure.page.PageObject;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.geekbang.projects.cs.middleground.customer.controller.vo.req.AddCustomerStaffReqVO;
import org.geekbang.projects.cs.middleground.customer.controller.vo.req.UpdateCustomerStaffReqVO;
import org.geekbang.projects.cs.middleground.customer.controller.vo.resp.CustomerStaffRespVO;
import org.geekbang.projects.cs.middleground.customer.converter.CustomerStaffConverter;
import org.geekbang.projects.cs.middleground.customer.entity.staff.CustomerStaff;
import org.geekbang.projects.cs.middleground.customer.service.ICustomerStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.List;

@RestController
@RequestMapping("/api/customerStaffs")
public class CustomerStaffSyncController {

    @Autowired
    ICustomerStaffService customerStaffService;

    @GetMapping("/sync/{systemId}")
    public Result<Boolean> syncOutsourcingCustomerStaffsBySystemId(@PathVariable("systemId") Long systemId) {

        //触发远程调用，获取客服信息并保存
        customerStaffService.syncOutsourcingCustomerStaffsBySystemId(systemId);

        return Result.success(true);
    }
}
