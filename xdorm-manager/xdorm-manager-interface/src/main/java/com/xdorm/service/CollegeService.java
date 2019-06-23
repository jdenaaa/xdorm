package com.xdorm.service;

import java.util.List;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.pojo.TbCollege;

public interface CollegeService {
	/**
	 * 获取全部学院
	 * @return
	 */
	List<TbCollege> getAllCollege();
	
	TbCollege getCollegeById(long collegeId);
	
	/**
	 * 添加学院
	 * @return
	 */
	XDormResult addCollege(TbCollege college);
	
	/**
	 * 通过collegeId删除学院
	 * @param collegeId
	 * @return
	 */
	XDormResult deleteCollegeById(long collegeId);
	
	/**
	 * 更新学院名
	 * @param collegeId
	 * @return
	 */
	XDormResult updateCollegeById(long collegeId,String collegeName);
}
