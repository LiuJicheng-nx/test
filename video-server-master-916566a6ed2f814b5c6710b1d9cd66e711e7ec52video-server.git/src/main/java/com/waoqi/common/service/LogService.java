package com.waoqi.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.waoqi.common.domain.LogDO;
import com.waoqi.common.domain.PageDO;
import com.waoqi.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
