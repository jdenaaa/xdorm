package com.xdorm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.common.utils.JsonUtils;
import com.xdorm.pojo.TbClass;
import com.xdorm.service.ClassService;

/**
 * 班级相关 Controller
 * @author ziran
 *
 */
@Controller
public class ClassController {
	
	@Autowired
	private ClassService classService;
	
	@RequestMapping("/class/all")
	@ResponseBody
	public List<TbClass> getAllClass(){
		List<TbClass> allClass = classService.getAllClass();
		return allClass;
	}
	
	@RequestMapping("/class/majorid")
	@ResponseBody
	public List<TbClass> getClassByMajorId(@RequestBody Map<String, Object> params){
		List<TbClass> classByMajorId = classService.getClassByMajorId(Long.valueOf((Integer)params.get("majorId")));
		return classByMajorId;
	}
	
	@RequestMapping("/class/{majorId}/{gradeId}")
	@ResponseBody
	public List<TbClass> getClassByMajorIdAndGradeId(@PathVariable Long majorId, @PathVariable Long gradeId) {
		List<TbClass> classByMajorIdAndGradeId = classService.getClassByMajorIdAndGradeId(majorId, gradeId);
		return classByMajorIdAndGradeId;
	}
	
	@RequestMapping(value = "/class/add", method = RequestMethod.POST)
	@ResponseBody
	public XDormResult addClass(@RequestBody Map<String, Object> params) {
		Object object = params.get("class");
		String classToJson = JsonUtils.objectToJson(object);
		TbClass tbClass = JsonUtils.jsonToPojo(classToJson, TbClass.class);
		XDormResult addClass = classService.addClass(tbClass);
		return addClass;
	}
	
	@RequestMapping(value = "/class/update", method = RequestMethod.POST)
	@ResponseBody
	public XDormResult updateClassById(@RequestBody Map<String, Object> params) {
		Object object = params.get("newClass");
		String classToJson = JsonUtils.objectToJson(object);
		TbClass newClass = JsonUtils.jsonToPojo(classToJson, TbClass.class);
		XDormResult result = classService.updateClassById(newClass);
		return result;
	}
	
	@RequestMapping(value = "/class/delete", method = RequestMethod.POST)
	@ResponseBody
	public XDormResult delClass(@RequestBody Map<String, Object> params) {
		Long classId = Long.valueOf((Integer)params.get("classId"));
		XDormResult result = classService.delClassById(classId);
		return result;
	}
}
