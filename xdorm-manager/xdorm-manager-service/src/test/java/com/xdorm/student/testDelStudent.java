package com.xdorm.student;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.common.utils.JsonUtils;
import com.xdorm.mapper.TbStudentMapper;

public class testDelStudent {
	/**
	 * 测试根据Id删除学生
	 */
	@Test
	public void tesDelStudentById() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbStudentMapper studentMapper = applicationContext.getBean(TbStudentMapper.class);
		int code = studentMapper.deleteByPrimaryKey("2");
		if(code == 1) {
			System.out.println(JsonUtils.objectToJson(XDormResult.build(200, "删除成功")));
		}else {
			System.out.println(JsonUtils.objectToJson(XDormResult.build(400, "删除失败")));
		}
		System.out.println(code);
	}

	@Test
	public void testDelStudentByIdList() {
		List<String> idList = new ArrayList<String>();
	    idList.add("10");
	    idList.add("11");
	    idList.add("12");
	    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
		TbStudentMapper studentMapper = applicationContext.getBean(TbStudentMapper.class);
		int code = studentMapper.deleteStudentByStuIdList(idList);
		System.out.println(code);
	}
}
