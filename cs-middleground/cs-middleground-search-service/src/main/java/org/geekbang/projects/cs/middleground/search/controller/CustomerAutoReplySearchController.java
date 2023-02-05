package org.geekbang.projects.cs.middleground.search.controller;

import org.geekbang.projects.cs.infrastructure.page.PageObject;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.geekbang.projects.cs.middleground.search.controller.vo.SearchParamReq;
import org.geekbang.projects.cs.middleground.search.entity.CustomerAutoReply;
import org.geekbang.projects.cs.middleground.search.service.CustomerAutoReplySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/customerAutoReply")
public class CustomerAutoReplySearchController {

    @Autowired
    private CustomerAutoReplySearchService customerAutoReplySearchService;

    @PostMapping("/search")
    public Result<PageObject<CustomerAutoReply>> search(@RequestBody SearchParamReq searchParamReq) throws IOException {
        Result<PageObject<CustomerAutoReply>> result = customerAutoReplySearchService.searchCustomerAutoReplies(searchParamReq);
        return result;
    }
}

