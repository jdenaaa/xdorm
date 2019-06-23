package com.xdorm.service;

import java.util.List;

import com.xdorm.pojo.TbGrade;

public interface GradeService {
	/**
	 * 获取全部年级
	 * @return
	 */
	List<TbGrade> getAllGrade();
}
