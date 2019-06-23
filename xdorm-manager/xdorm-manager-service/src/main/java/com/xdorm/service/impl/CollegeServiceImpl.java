package com.xdorm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.mapper.TbCollegeMapper;
import com.xdorm.pojo.TbCollege;
import com.xdorm.pojo.TbCollegeExample;
import com.xdorm.pojo.TbCollegeExample.Criteria;
import com.xdorm.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	private TbCollegeMapper collegeMapper;

	/**
	 * 获取全部学院
	 */
	@Override
	public List<TbCollege> getAllCollege() {
		TbCollegeExample example = new TbCollegeExample();
		List<TbCollege> college = collegeMapper.selectByExample(example);
		return college;
	}

	/**
	 * 根据id获取学院
	 */
	@Override
	public TbCollege getCollegeById(long collegeId) {
		TbCollege college = collegeMapper.selectByPrimaryKey(collegeId);
		return college;
	}

	/**
	 * 添加学院
	 */
	@Override
	public XDormResult addCollege(TbCollege college) {
		try {
			TbCollegeExample example = new TbCollegeExample();
			Criteria criteria = example.createCriteria();
			criteria.andCollegeNameEqualTo(college.getCollegeName());
			List<TbCollege> collegeList = collegeMapper.selectByExample(example);
			if(collegeList.size() >= 1) {
				return XDormResult.build(400, "添加失败：该学院已存在");
			}
			int code = collegeMapper.insert(college);
			if (code != 0) {
				return XDormResult.build(200, "添加学院成功");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return XDormResult.build(400, "意外错误，错误代码：addcollege");
	}

	/**
	 * 根据id删除学院
	 */
	@Override
	public XDormResult deleteCollegeById(long collegeId) {
		try {
			int code = collegeMapper.deleteByPrimaryKey(collegeId);
			if(code >= 1) {
				return XDormResult.build(200, "删除学院成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return XDormResult.build(400, "删除学院失败，错误代码：delcollege");
	}

	/**
	 * 根据id更新学院名
	 */
	@Override
	public XDormResult updateCollegeById(long collegeId,String collegeName) {
		//检查是否已存在该学院名称
		
		TbCollegeExample example = new TbCollegeExample();
		Criteria criteria = example.createCriteria();
		criteria.andCollegeNameEqualTo(collegeName);
		List<TbCollege> collegeList = collegeMapper.selectByExample(example);
		
		if(collegeList.size() >= 1) {
			return XDormResult.build(400, "更新失败：该学院已存在");
		}
		TbCollege newCollege = new TbCollege();
		newCollege.setCollegeId(collegeId);
		newCollege.setCollegeName(collegeName);
		int code = collegeMapper.updateByPrimaryKey(newCollege);
		if(code != 0) {
			return XDormResult.build(200, "更新学院信息成功");
		}
		return XDormResult.build(400, "更新学院信息失败：错误代码 updatecollege");
	}

}
