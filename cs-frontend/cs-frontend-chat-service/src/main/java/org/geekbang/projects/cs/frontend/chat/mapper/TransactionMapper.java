package org.geekbang.projects.cs.frontend.chat.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.geekbang.projects.cs.frontend.chat.entity.Transaction;

public interface TransactionMapper extends BaseMapper<Transaction> {

    default void updateBranchTransactionToCommitted(String xid, Long branchId) {
        LambdaQueryWrapper<Transaction> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Transaction::getXid, xid);
        queryWrapper.eq(Transaction::getBranchId, branchId);
        Transaction transaction = this.selectOne(queryWrapper);
        transaction.setState(2);
        this.updateById(transaction);
    }

    default void updateBranchTransactionToRollbacked(String xid, Long branchId) {
        LambdaQueryWrapper<Transaction> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Transaction::getXid, xid);
        queryWrapper.eq(Transaction::getBranchId, branchId);
        Transaction transaction = this.selectOne(queryWrapper);
        transaction.setState(3);
        this.updateById(transaction);
    }

    default Transaction load(String xid, Long branchId) {
        LambdaQueryWrapper<Transaction> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Transaction::getXid, xid);
        queryWrapper.eq(Transaction::getBranchId, branchId);
        Transaction transaction = this.selectOne(queryWrapper);
        return transaction;
    }
}
