package com.xdorm.pagehelper;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdorm.common.utils.JsonUtils;
import com.xdorm.mapper.TbDormMapper;
import com.xdorm.mapper.TbStudentMapper;
import com.xdorm.pojo.TbDorm;
import com.xdorm.pojo.TbStudent;
import com.xdorm.pojo.TbStudentExample;

public class TestPageHelper {
	
//	@Test
//	public void testPagehelper() {
//		PageHelper.startPage(1, 10);
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
//		TbStudentMapper sutdentMapper = applicationContext.getBean(TbStudentMapper.class);
//		
//		TbStudentExample example = new TbStudentExample();
//		List<TbStudent> list = sutdentMapper.selectByExample(example);
//
//		PageInfo<TbStudent> pageInfo = new PageInfo<TbStudent>(list);
//		String objectToJson = JsonUtils.objectToJson(pageInfo);
//		System.out.println(objectToJson);
//	}
////	
//	@Test
//	public void testGetStudentById() {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
//		TbStudentMapper studentMapper = applicationContext.getBean(TbStudentMapper.class);
//		TbStudent student = studentMapper.selectByPrimaryKey("111");
//		System.out.println(student);
//	}

}
