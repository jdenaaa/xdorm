package com.xdorm.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.xdorm.pojo.TbDorm;
import com.xdorm.service.DormService;

@Controller
public class DormController {
	@Autowired
	private DormService dormService;
	
	@RequestMapping("/dorm/all")
	@ResponseBody
	public List<TbDorm> getAllDorm(){
		List<TbDorm> allDorm = dormService.getAllDorm();
		return allDorm;
	}
	
	/**
	 * 根据 公寓id 获取公寓的层数 生成 层列表 [1,2,3,4.......]
	 * @param dormid
	 * @return
	 */
	@RequestMapping("/dorm/dormid/{dormId}")
	@ResponseBody
	public List<Integer> getDormFloor(@PathVariable Long dormId){
		TbDorm dorm = dormService.getDormById(dormId);
		Integer floorTotal = dorm.getFloorTotal();
		
		List<Integer> floorList = new ArrayList<Integer>();
		for(int i = 1;i <= floorTotal;i++) {
			floorList.add(i);
		}
		return floorList;
	}
	
	@RequestMapping(value = "/dorm/update", method = RequestMethod.POST)
	@ResponseBody
	public XDormResult updateDormById(@RequestBody Map<String, Object> params) {
		Object object = params.get("newDorm");
		String dormToJson = JsonUtils.objectToJson(object);
		TbDorm dorm = JsonUtils.jsonToPojo(dormToJson, TbDorm.class);
		XDormResult result = dormService.updateDormById(dorm);
		return result;
	}
	
	@RequestMapping(value = "/dorm/delete", method = RequestMethod.POST)
	@ResponseBody
	public XDormResult delDormById(@RequestBody Map<String, Object> params) {
		Long dormId = Long.valueOf((Integer)params.get("dormId"));
		XDormResult result = dormService.delDormById(dormId);
		return result;
	}
	
	@RequestMapping(value = "/dorm/add", method = RequestMethod.POST)
	@ResponseBody
	public XDormResult addDorm(@RequestBody Map<String, Object> params) {
		Object object = params.get("dorm");
		String dormToJson = JsonUtils.objectToJson(object);
		TbDorm dorm = JsonUtils.jsonToPojo(dormToJson, TbDorm.class);
		XDormResult result = dormService.addDorm(dorm);
		return result;
	}
	
}
