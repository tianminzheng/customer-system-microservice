package org.geekbang.projects.cs.integration.servicebus.router;

import java.util.List;

public interface OutsourcingSystemRouter<T> {

    List<T> fetchOutsourcingCustomerStaffs(String systemUrl);
}
