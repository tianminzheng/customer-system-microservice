package org.geekbang.projects.cs.frontend.business.action.impl;

import com.alibaba.fastjson.JSON;
import io.seata.rm.tcc.api.BusinessActionContext;
import org.geekbang.projects.cs.frontend.business.action.CreateTicketTccAction;
import org.geekbang.projects.cs.frontend.business.client.TicketClient;
import org.geekbang.projects.cs.frontend.business.controller.vo.AddTicketReqVO;
import org.geekbang.projects.cs.infrastructure.exception.BizException;
import org.geekbang.projects.cs.infrastructure.exception.MessageCode;
import org.geekbang.projects.cs.infrastructure.tcc.IdempotenceUtils;
import org.geekbang.projects.cs.infrastructure.tcc.TccRequest;
import org.geekbang.projects.cs.infrastructure.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateTicketTccActionImpl implements CreateTicketTccAction {

    @Autowired
    private TicketClient ticketClient;

    @Override
    public boolean prepare(BusinessActionContext actionContext, String addTicketReqVO) throws BizException {

        AddTicketReqVO requestData = JSON.parseObject(addTicketReqVO, AddTicketReqVO.class);

        //数据验证
        if (requestData == null || requestData.getUserId() == null || requestData.getStaffId() == null) {
            throw new BizException(MessageCode.CHECK_ERROR);
        }

        TccRequest<AddTicketReqVO> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(requestData);

        ticketClient.ticketTry(tccRequest);

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

        AddTicketReqVO requestData = JSON.parseObject(actionContext.getActionContext("addTicketReqVO").toString(), AddTicketReqVO.class);

        TccRequest<String> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(requestData.getTicketNo());

        ticketClient.ticketConfirm(tccRequest);

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

        AddTicketReqVO requestData = JSON.parseObject(actionContext.getActionContext("addTicketReqVO").toString(), AddTicketReqVO.class);

        TccRequest<String> tccRequest = new TccRequest<>();
        tccRequest.setXid(actionContext.getXid());
        tccRequest.setBranchId(actionContext.getBranchId());
        tccRequest.setData(requestData.getTicketNo());

        ticketClient.ticketCancel(tccRequest);

        // cancel 成功删除标识
        IdempotenceUtils.removeResult(getClass(), actionContext.getXid());

        return true;
    }
}
