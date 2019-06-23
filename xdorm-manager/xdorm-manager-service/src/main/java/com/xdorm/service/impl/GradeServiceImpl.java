package com.xdorm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdorm.mapper.TbGradeMapper;
import com.xdorm.pojo.TbGrade;
import com.xdorm.pojo.TbGradeExample;
import com.xdorm.service.GradeService;

@Service
public class GradeServiceImpl implements GradeService {
	
	@Autowired
	private TbGradeMapper gradeMapper;

	@Override
	public List<TbGrade> getAllGrade() {
		TbGradeExample example = new TbGradeExample();
		List<TbGrade> gradeList = gradeMapper.selectByExample(example);
		return gradeList;
	}

}
