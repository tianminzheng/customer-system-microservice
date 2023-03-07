package org.geekbang.projects.cs.frontend.ticket.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.geekbang.projects.cs.frontend.ticket.controller.vo.AddTicketReqVO;
import org.geekbang.projects.cs.frontend.ticket.converter.CustomerTicketConverter;
import org.geekbang.projects.cs.frontend.ticket.entity.CustomerTicket;
import org.geekbang.projects.cs.frontend.ticket.entity.Transaction;
import org.geekbang.projects.cs.frontend.ticket.mapper.CustomerTicketMapper;
import org.geekbang.projects.cs.frontend.ticket.mapper.TransactionMapper;
import org.geekbang.projects.cs.frontend.ticket.service.ICustomerTicketService;
import org.geekbang.projects.cs.infrastructure.exception.BizException;
import org.geekbang.projects.cs.infrastructure.exception.MessageCode;
import org.geekbang.projects.cs.infrastructure.tcc.TccRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客服工单表 服务实现类
 * </p>
 */
@Service
public class CustomerTicketServiceImpl extends ServiceImpl<CustomerTicketMapper, CustomerTicket> implements ICustomerTicketService {

    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    CustomerTicketMapper customerTicketMapper;

    @Override
    public void insertTicket(TccRequest<AddTicketReqVO> tccRequest) throws BizException {

        //防止服务悬挂
        Transaction existTransaction = transactionMapper.load(tccRequest.getXid(),tccRequest.getBranchId());
        if(existTransaction!=null) {
            throw new BizException(MessageCode.CHECK_ERROR, "该事务已经提交");
        }

        Transaction transaction = new Transaction();
        transaction.setXid(tccRequest.getXid());
        transaction.setBranchId(tccRequest.getBranchId());
        transaction.setData(JSON.toJSONString(tccRequest.getData()));
        transaction.setState(1);
        transactionMapper.insert(transaction);


        CustomerTicket customerTicket = CustomerTicketConverter.INSTANCE.convertVO(tccRequest.getData());
        customerTicket.setTccStatus(0);
        customerTicketMapper.insert(customerTicket);
    }

    @Override
    public void updateTicketSuccessStatus(TccRequest<String> ticketNo) {
        transactionMapper.updateBranchTransactionToCommitted(ticketNo.getXid(),ticketNo.getBranchId());

        customerTicketMapper.updateCustomerTicketTccStatus(ticketNo.getData(), 1);
    }

    @Override
    public void updateTicketFailStatus(TccRequest<String> ticketNo) {

        //允许空回滚
        Transaction existTransaction = transactionMapper.load(ticketNo.getXid(),ticketNo.getBranchId());
        if(existTransaction == null) {
            Transaction transaction = new Transaction();
            transaction.setXid(ticketNo.getXid());
            transaction.setBranchId(ticketNo.getBranchId());
            transaction.setData(JSON.toJSONString(ticketNo.getData()));

            //已经回滚
            transaction.setState(3);
            transactionMapper.insert(transaction);
            return;
        }

        transactionMapper.updateBranchTransactionToRollbacked(ticketNo.getXid(),ticketNo.getBranchId());

        customerTicketMapper.updateCustomerTicketTccStatus(ticketNo.getData(), 2);
    }
}
