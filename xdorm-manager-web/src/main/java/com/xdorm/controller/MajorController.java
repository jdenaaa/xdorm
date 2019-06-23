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
import com.xdorm.pojo.TbCollege;
import com.xdorm.pojo.TbMajor;
import com.xdorm.service.MajorService;

@Controller
public class MajorController {
	
	@Autowired
	private MajorService majorService;
	
	@RequestMapping("/major/all")
	@ResponseBody
	public List<TbMajor> getAllMajor(){
		List<TbMajor> allMajor = majorService.getAllMajor();
		return allMajor;
	}
	/**
	 * 根据前端传来的 collegeId 返回该学院 专业
	 * @param params
	 * @return
	 */
	@RequestMapping("/major/collegeid/{collegeId}")
	@ResponseBody
	public List<TbMajor> getMajorByCollegeId(@PathVariable Long collegeId){
		List<TbMajor> major = majorService.getMajorByCollegeId(collegeId);
		return major;
	}
	
	@RequestMapping(value = "/major/add",method = RequestMethod.POST)
	@ResponseBody
	public XDormResult addMajor(@RequestBody Map<String, Object> params) {
		Object object = params.get("major");
		String jsonCollege = JsonUtils.objectToJson(object);
		TbMajor tbMajor = JsonUtils.jsonToPojo(jsonCollege, TbMajor.class);
		XDormResult addMajor = majorService.addMajor(tbMajor);
		return addMajor;
	}
	
	@RequestMapping(value = "/major/delete",method = RequestMethod.POST)
	@ResponseBody
	public XDormResult delMajorById(@RequestBody Map<String, Object> params) {
		Long majorId = Long.valueOf((Integer)params.get("majorId"));
		XDormResult delMajor = majorService.delMajorById(majorId);
		return delMajor;
	}
	
	@RequestMapping(value = "/major/update",method = RequestMethod.POST)
	@ResponseBody
	public XDormResult updateMajorByExample(@RequestBody Map<String, Object> params) {
		Object object = params.get("newMajor");
		String majorToJson = JsonUtils.objectToJson(object);
		TbMajor newMajor = JsonUtils.jsonToPojo(majorToJson, TbMajor.class);
		
		XDormResult result = majorService.updateMajorById(newMajor);
		return result;
	}
}
