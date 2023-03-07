package org.geekbang.projects.cs.frontend.business.action.impl;

import com.alibaba.fastjson.JSON;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.geekbang.projects.cs.frontend.business.action.CreateChatTccAction;
import org.geekbang.projects.cs.frontend.business.client.ChatClient;
import org.geekbang.projects.cs.frontend.business.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.infrastructure.exception.BizException;
import org.geekbang.projects.cs.infrastructure.tcc.IdempotenceUtils;
import org.geekbang.projects.cs.infrastructure.tcc.TccRequest;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateChatTccActionImpl implements CreateChatTccAction {

    @Autowired
    private ChatClient chatClient;

    @Override
    public boolean prepare(BusinessActionContext actionContext, String addChatReqVO) throws BizException {

        AddChatReqVO requestData = JSON.parseObject(addChatReqVO, AddChatReqVO.class);

        TccRequest<AddChatReqVO> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(requestData);

        chatClient.chatTry(tccRequest);

        //事务成功，保存一个标识，供第二阶段进行判断
        IdempotenceUtils.setResult(getClass(), actionContext.getXid(), "success");

        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {

        // 幂等控制，如果commit阶段重复执行则直接返回
        if (IdempotenceUtils.getResult(getClass(), actionContext.getXid()) == null) {
            return true;
        }

        AddChatReqVO requestData = JSON.parseObject(actionContext.getActionContext("addChatReqVO").toString(), AddChatReqVO.class);

        TccRequest<String> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(requestData.getTicketNo());

        chatClient.chatConfirm(tccRequest);

        // commit成功删除标识
        IdempotenceUtils.removeResult(getClass(), actionContext.getXid());

        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {

        // 幂等控制，如果cancel阶段重复执行则直接返回
        if (IdempotenceUtils.getResult(getClass(), actionContext.getXid()) == null) {
            return true;
        }

        AddChatReqVO requestData = JSON.parseObject(actionContext.getActionContext("addChatReqVO").toString(), AddChatReqVO.class);

        TccRequest<String> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(requestData.getTicketNo());

        Result<Boolean> result = chatClient.chatCancel(tccRequest);

        // cancel 成功删除标识
        IdempotenceUtils.removeResult(getClass(), actionContext.getXid());
        return true;
    }
}
