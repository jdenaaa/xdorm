package com.xdorm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.mapper.TbDormMapper;
import com.xdorm.pojo.TbDorm;
import com.xdorm.pojo.TbDormExample;
import com.xdorm.pojo.TbDormExample.Criteria;
import com.xdorm.service.DormService;

@Service
public class DormServiceImpl implements DormService {
	
	@Autowired
	private TbDormMapper dormMapper;
	
	@Override
	public List<TbDorm> getAllDorm() {
		TbDormExample example = new TbDormExample();
		List<TbDorm> allDorm = dormMapper.selectByExample(example);
		return allDorm;
	}

	@Override
	public TbDorm getDormById(long dormId) {
		TbDorm dorm = dormMapper.selectByPrimaryKey(dormId);
		return dorm;
	}

	@Override
	public XDormResult updateDormById(TbDorm dorm) {
		String dormName = dorm.getDormName();
		TbDormExample example = new TbDormExample();
		Criteria criteria = example.createCriteria();
		criteria.andDormNameEqualTo(dormName);
		List<TbDorm> dormList = dormMapper.selectByExample(example);
		
		if (dormList.size() > 0) {
			return XDormResult.build(400, "修改失败：该公寓名称已存在");
		}
		int code = dormMapper.updateByPrimaryKey(dorm);
		if(code != 0) {
			return XDormResult.build(200, "修改成功");
		}
		return XDormResult.build(400,"系统异常：updatedorm");
	}

	@Override
	public XDormResult delDormById(long dormId) {
		try {
			int code = dormMapper.deleteByPrimaryKey(dormId);
			if(code != 0) {
				return XDormResult.build(200, "删除公寓成功");
			}
		} catch (Exception e) {
			return XDormResult.build(400, "系统错误：删除失败");
		}
		return XDormResult.build(400, "删除失败");
	}

	@Override
	public XDormResult addDorm(TbDorm dorm) {
		try {
			//检查是否名称重复
			String dormName = dorm.getDormName();
			TbDormExample example = new TbDormExample();
			Criteria criteria = example.createCriteria();
			criteria.andDormNameEqualTo(dormName);
			List<TbDorm> dormList = dormMapper.selectByExample(example);
			if(dormList.size() > 0) {
				return XDormResult.build(400, "公寓已存在，无需重复添加");
			}
			
			int code = dormMapper.insert(dorm);
			if (code == 1) {
				return XDormResult.build(200, "添加公寓成功");
			}
		} catch (Exception e) {
			return XDormResult.build(400, "系统错误：添加失败-错误代码adddorm");
		}
		return XDormResult.build(400, "添加失败");
	}

}
