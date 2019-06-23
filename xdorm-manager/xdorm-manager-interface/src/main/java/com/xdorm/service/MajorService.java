package com.xdorm.service;

import java.util.List;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.pojo.TbMajor;

public interface MajorService {
	List<TbMajor> getAllMajor();
	
	/**
	 * 根据前端传来的 collegeId 返回该学院 专业
	 * @param collegeId
	 * @return
	 */
	List<TbMajor> getMajorByCollegeId(long collegeId);
	
	/**
	 * 添加学院
	 * @param major
	 * @return
	 */
	XDormResult addMajor(TbMajor major);
	
	/**
	 * 删除学院
	 * @param majorId
	 * @return
	 */
	XDormResult delMajorById(long majorId);
	
	XDormResult updateMajorById(TbMajor major);
}
