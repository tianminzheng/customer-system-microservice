package org.geekbang.projects.cs.frontend.chat.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.geekbang.projects.cs.frontend.chat.entity.TxRecord;

import java.time.LocalDateTime;

public interface TxRecordMapper extends BaseMapper<TxRecord> {

    default TxRecord findTxRecordByTxNo(String txNo) {
        LambdaQueryWrapper<TxRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TxRecord::getTxNo, txNo);

        return selectOne(queryWrapper);
    }

    default void addTxRecord(String txNo) {
        TxRecord txRecord = new TxRecord().setTxNo(txNo).setCreateTime(LocalDateTime.now());

        insert(txRecord);
    }
}
