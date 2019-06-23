package com.xdorm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.mapper.TbUserMapper;
import com.xdorm.pojo.TbUser;
import com.xdorm.pojo.TbUserExample;
import com.xdorm.pojo.TbUserExample.Criteria;
import com.xdorm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private TbUserMapper userMapper;

	@Override
	public XDormResult login(String username, String password) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo(username);
		List<TbUser> userList = userMapper.selectByExample(example);
		if(userList == null || userList.size() == 0) {
			return XDormResult.build(400, "用户名或密码错误");
		}
		TbUser user = userList.get(0);
		if(!user.getPassword().equals(password)) {
			return XDormResult.build(400, "用户名或密码错误");
		}
		return XDormResult.build(200, "登录成功");
	}

}
