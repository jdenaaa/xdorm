package com.xdorm.service;

import java.util.List;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.pojo.TbDorm;

public interface DormService {
	List<TbDorm> getAllDorm();
	/**
	 *  根据dorm_id 获取 公寓 楼
	 * @param dormId
	 * @return
	 */
	TbDorm getDormById(long dormId);
	
	XDormResult updateDormById(TbDorm dorm);
	
	XDormResult delDormById(long dormId);
	
	XDormResult addDorm(TbDorm dorm);
	
}
