package com.xdorm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user/login",method = RequestMethod.POST)
	@ResponseBody
	public XDormResult login(@RequestBody Map<String, Object> params) {
		String username = (String)params.get("username");
		String password = (String)params.get("password");
		XDormResult result = userService.login(username, password);
		return result;
	}
	
}
