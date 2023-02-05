package org.geekbang.projects.cs.middleground.task.feign;

import org.geekbang.projects.cs.infrastructure.vo.Result;

public class CustomerStaffSyncClientFallback implements CustomerStaffSyncClient {

    @Override
    public Result<Boolean> syncOutsourcingCustomerStaffsBySystemId(Long systemId) {
        return Result.success(true);
    }
}
