package com.xdorm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.common.utils.JsonUtils;
import com.xdorm.pojo.TbCollege;
import com.xdorm.service.CollegeService;

@Controller
public class CollegeController {
	@Autowired
	private CollegeService collegeService;
	
	/**
	 * 返回全部学院
	 * @return
	 */
	@RequestMapping("/college/all")
	@ResponseBody
	public List<TbCollege> getAllCollege() {
		List<TbCollege> college = collegeService.getAllCollege();
		return college;
	}
	
	@RequestMapping(value = "/college/add",method = RequestMethod.POST)
	@ResponseBody
	public XDormResult addCollege(@RequestBody Map<String, Object> params) {
		Object object = params.get("college");
		String jsonCollege = JsonUtils.objectToJson(object);
		TbCollege tbCollege = JsonUtils.jsonToPojo(jsonCollege, TbCollege.class);
		XDormResult addCollege = collegeService.addCollege(tbCollege);
		return addCollege;
	}
	
	@RequestMapping(value = "/college/delete",method = RequestMethod.POST)
	@ResponseBody
	public XDormResult deleteCollegeById(@RequestBody Map<String, Object> params) {
		Long collegeId = Long.valueOf((Integer)params.get("collegeId"));
		XDormResult xDResult = collegeService.deleteCollegeById(collegeId);
		return xDResult;
	}
	
	@RequestMapping(value = "/college/update",method = RequestMethod.POST)
	@ResponseBody
	public XDormResult updateCollegeById(@RequestBody Map<String, Object> params) {
		String collegeToJson = JsonUtils.objectToJson(params.get("newCollege"));
		TbCollege newCollege = JsonUtils.jsonToPojo(collegeToJson, TbCollege.class);
		Long collegeId = newCollege.getCollegeId();
		String newCollegeName = newCollege.getCollegeName();
		XDormResult xDResult = collegeService.updateCollegeById(collegeId,newCollegeName);
		return xDResult;
	}
	
//	@RequestMapping("/college")
//	@ResponseBody
//	public TbCollege getCollegeById(long collegeId) {
//		TbCollege college = collegeService.getCollegeById(collegeId);
//		return college; 
//	}
	
	
}
