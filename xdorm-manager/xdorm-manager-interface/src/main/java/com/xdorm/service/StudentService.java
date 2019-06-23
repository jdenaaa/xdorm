package com.xdorm.service;

import java.util.List;

import com.xdorm.common.pojo.StudentPageResult;
import com.xdorm.common.pojo.XDormResult;
import com.xdorm.pojo.TbStudent;

public interface StudentService {
	/**
	 * 根据页码获取student列表，每页返回10
	 */
	StudentPageResult getStudentList(int page);
	
	
	/**
	 * 根据 sutuId 删除学生
	 * @param stuId
	 * @return
	 */
	XDormResult delStudentById(String stuId);
	
	/**
	 * 删除一个 stuId 列表中的student
	 * @param stuIdList
	 * @return
	 */
	XDormResult delStudentByIdList(List<String> stuIdList);
	
	/**
	 * 添加一个学生
	 * @return
	 */
	XDormResult addStudent(TbStudent student);
	
	/**
	 * 根据id获取学生信息
	 * @param stuId
	 * @return
	 */
	TbStudent getStudentById(String stuId);
	
	XDormResult updateStudentById(TbStudent student);
}
