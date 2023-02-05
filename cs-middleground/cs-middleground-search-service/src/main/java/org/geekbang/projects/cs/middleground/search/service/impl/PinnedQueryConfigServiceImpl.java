package org.geekbang.projects.cs.middleground.search.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.geekbang.projects.cs.middleground.search.entity.PinnedQueryConfig;
import org.geekbang.projects.cs.middleground.search.mapper.PinnedQueryConfigMapper;
import org.geekbang.projects.cs.middleground.search.service.PinnedQueryConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class PinnedQueryConfigServiceImpl extends ServiceImpl<PinnedQueryConfigMapper, PinnedQueryConfig> implements PinnedQueryConfigService {

	@Autowired
	private PinnedQueryConfigMapper pinnedQueryConfigMapper;

	@Override
	public PinnedQueryConfig findActivePinnedQueryConfigBySubjectWord(String subjectWord, Integer businessType) {
		PinnedQueryConfig pinnedQueryConfig = pinnedQueryConfigMapper.findActivePinnedQueryConfigBySubjectWord(businessType, subjectWord);

		if(!Objects.isNull(pinnedQueryConfig)) {
			log.info("获取PinnedQueryConfig：" + pinnedQueryConfig.toString());
		}
		return pinnedQueryConfig;
	}

}
