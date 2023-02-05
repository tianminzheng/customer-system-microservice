package org.geekbang.projects.cs.middleground.api.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerStaffApiFallback implements CustomerStaffApi {
    @Override
    public void syncOutsourcingCustomerStaffsBySystemId(Long systemId) {

    }
}
