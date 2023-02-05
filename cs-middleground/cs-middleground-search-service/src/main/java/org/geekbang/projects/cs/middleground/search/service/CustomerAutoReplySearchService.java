package org.geekbang.projects.cs.middleground.search.service;


import org.geekbang.projects.cs.infrastructure.page.PageObject;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.geekbang.projects.cs.middleground.search.controller.vo.SearchParamReq;
import org.geekbang.projects.cs.middleground.search.entity.CustomerAutoReply;

import java.io.IOException;

public interface CustomerAutoReplySearchService {

    Result<PageObject<CustomerAutoReply>> searchCustomerAutoReplies(SearchParamReq searchParamReq) throws IOException;
}
