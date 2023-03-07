package org.geekbang.projects.cs.frontend.chat.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.geekbang.projects.cs.frontend.chat.controller.vo.AddChatReqVO;
import org.geekbang.projects.cs.frontend.chat.converter.ChatRecordConverter;
import org.geekbang.projects.cs.frontend.chat.entity.ChatRecord;
import org.geekbang.projects.cs.frontend.chat.entity.Transaction;
import org.geekbang.projects.cs.frontend.chat.mapper.ChatRecordMapper;
import org.geekbang.projects.cs.frontend.chat.mapper.TransactionMapper;
import org.geekbang.projects.cs.frontend.chat.service.IChatRecordService;
import org.geekbang.projects.cs.infrastructure.exception.BizException;
import org.geekbang.projects.cs.infrastructure.exception.MessageCode;
import org.geekbang.projects.cs.infrastructure.tcc.TccRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聊天记录主表 服务实现类
 * </p>
 */
@Service
public class ChatRecordServiceImpl extends ServiceImpl<ChatRecordMapper, ChatRecord> implements IChatRecordService {

    @Autowired
    TransactionMapper transactionMapper;

    @Autowired
    ChatRecordMapper chatRecordMapper;

    @Override
    public void insertChat(TccRequest<AddChatReqVO> tccRequest) throws BizException {

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

        ChatRecord chatRecord = ChatRecordConverter.INSTANCE.convertVO(tccRequest.getData());
        chatRecord.setTccStatus(0);
        chatRecordMapper.insert(chatRecord);
    }

    @Override
    public void updateChatSuccessStatus(TccRequest<String> ticketNo) {
        transactionMapper.updateBranchTransactionToCommitted(ticketNo.getXid(),ticketNo.getBranchId());

        chatRecordMapper.updateChatRecordTccStatus(ticketNo.getData(), 1);
    }

    @Override
    public void updateChatFailStatus(TccRequest<String> ticketNo) {
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

        chatRecordMapper.updateChatRecordTccStatus(ticketNo.getData(), 2);
    }
}
