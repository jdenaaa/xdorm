package com.xdorm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdorm.pojo.TbGrade;
import com.xdorm.service.GradeService;

@Controller
public class GradeController {

	@Autowired
	private GradeService gradeService;
	
	@RequestMapping("/grade/all")
	@ResponseBody
	public List<TbGrade> getAllGrade(){
		List<TbGrade> allGrade = gradeService.getAllGrade();
		return allGrade;
	}
}
