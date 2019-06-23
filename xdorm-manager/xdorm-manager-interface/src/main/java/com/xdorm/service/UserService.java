package com.xdorm.service;

import com.xdorm.common.pojo.XDormResult;

public interface UserService {
	XDormResult login(String username, String password);
}
