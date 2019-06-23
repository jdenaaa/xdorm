package com.xdorm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdorm.common.pojo.StudentPageResult;
import com.xdorm.common.pojo.XDormResult;
import com.xdorm.common.utils.JsonUtils;
import com.xdorm.pojo.TbStudent;
import com.xdorm.service.StudentService;
/**
 * Student Controller
 * @author ziran
 *
 */
@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	/**
	 * Post请求 学生分页信息 
	 * @param params  page 页码
	 * @return
	 */
	@RequestMapping(value = "/student/list",method = RequestMethod.POST)
	@ResponseBody
	public StudentPageResult getStudentList(@RequestBody Map<String, Object> params) {
		StudentPageResult result = studentService.getStudentList((Integer)params.get("page"));
		return result;
	}
	/**
	 * POST 请求根据 ID 删除一个学生
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/student/delete",method = RequestMethod.POST)
	@ResponseBody
	public XDormResult delStudentById(@RequestBody Map<String, Object> params) {
		XDormResult result = studentService.delStudentById((String)params.get("stuId"));
		return result;
	}
	
	@RequestMapping(value = "/student/batchdelte", method = RequestMethod.POST)
	@ResponseBody
	public XDormResult delStudentByIdList(@RequestBody Map<String, Object> params) {
		XDormResult result = studentService.delStudentByIdList((List<String>) params.get("stuIdList"));
		return result;
	}
	
	/**
	 * 添加一个学生
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/student/add", method = RequestMethod.POST)
	@ResponseBody
	public XDormResult addStudent(@RequestBody Map<String, Object> params) {
		Object object = params.get("student");
		String jsonStudent = JsonUtils.objectToJson(object);
		TbStudent tbStudent = JsonUtils.jsonToPojo(jsonStudent, TbStudent.class);
		XDormResult result = studentService.addStudent(tbStudent);
//		System.out.println(jsonStudent);
//		System.out.println(JsonUtils.objectToJson(result));
		return result;
	}
	
	@RequestMapping(value = "/student/getstu", method = RequestMethod.POST)
	@ResponseBody
	public TbStudent getStudentById(@RequestBody Map<String, Object> params) {
		String stuId = (String)params.get("stuId");
		TbStudent student = studentService.getStudentById(stuId);
		return student;
	}
	
	@RequestMapping(value = "/student/update", method = RequestMethod.POST)
	@ResponseBody
	public XDormResult updateStudentById(@RequestBody Map<String, Object> params){
		Object object = params.get("newStudent");
		String studentToJson = JsonUtils.objectToJson(object);
		TbStudent newStudent = JsonUtils.jsonToPojo(studentToJson, TbStudent.class);
		XDormResult result = studentService.updateStudentById(newStudent);
		return result;
	}
	
	/**
	 * GET请求 根据Id删除student的测试
	 * @param stuId
	 * @return
	 */
//	@RequestMapping("/student/delete/get")
//	@ResponseBody
//	public XDormResult delStudentBuId(String stuId) {
//		XDormResult xDormResult = studentService.delStudentById(stuId);
//		return xDormResult;
//	}
	/**
	 * 浏览器get 请求学生分页信息测试 
	 * @param page
	 * @return
	 */
//	@RequestMapping(value = "/student/list/get")
//	@ResponseBody
//	public StudentPageResult getStudentList(Integer page) {
//		StudentPageResult result = studentService.getStudentList(page);
//		String json = JsonUtils.objectToJson(result);
//		return result;
//	}
	
}
