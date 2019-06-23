package com.xdorm.service;

import java.util.List;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.pojo.TbClass;

public interface ClassService {
	List<TbClass> getAllClass();
	
	/**
	 * 根据 majorId 获取该专业 的班级
	 * @param id
	 * @return
	 */
	List<TbClass> getClassByMajorId(long majorId);
	
	/**
	 * 根据majorId和grade筛选班级
	 * @param majorId
	 * @param gradeId
	 * @return
	 */
	List<TbClass> getClassByMajorIdAndGradeId(long majorId, long gradeId);
	
	/**
	 * 添加班级
	 * @param tbClass
	 * @return
	 */
	XDormResult addClass(TbClass tbClass);
	
	/**
	 * 修改班级信息
	 * @param tbClass
	 * @return
	 */
	XDormResult updateClassById(TbClass tbClass);
	
	/**
	 * 根据id删除班级
	 * @param classId
	 * @return
	 */
	XDormResult delClassById(long classId);
}
