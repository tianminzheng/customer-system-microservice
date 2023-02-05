package org.geekbang.projects.cs.middleground.search.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.geekbang.projects.cs.middleground.search.entity.PinnedQueryConfig;

public interface PinnedQueryConfigMapper extends BaseMapper<PinnedQueryConfig> {

	default PinnedQueryConfig findActivePinnedQueryConfigBySubjectWord(Integer businessType, String subjectWord) {
		LambdaQueryWrapper<PinnedQueryConfig> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(PinnedQueryConfig::getSubjectWord, subjectWord);
		queryWrapper.eq(PinnedQueryConfig::getBusinessType, businessType);

		return selectOne(queryWrapper);
	}
}