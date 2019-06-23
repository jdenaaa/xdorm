package com.xdorm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.mapper.TbMajorMapper;
import com.xdorm.pojo.TbMajor;
import com.xdorm.pojo.TbMajorExample;
import com.xdorm.pojo.TbMajorExample.Criteria;
import com.xdorm.service.MajorService;

@Service
public class MajorServiceImpl implements MajorService {

	@Autowired
	private TbMajorMapper majorMapper;

	@Override
	public List<TbMajor> getAllMajor() {
		TbMajorExample example = new TbMajorExample();
		List<TbMajor> allMajor = majorMapper.selectByExample(example);
		return allMajor;
	}

	@Override
	public List<TbMajor> getMajorByCollegeId(long collegeId) {
		TbMajorExample example = new TbMajorExample();
		Criteria criteria = example.createCriteria();
		criteria.andCollegeIdEqualTo(collegeId);
		List<TbMajor> majorByCollegeId = majorMapper.selectByExample(example);
		return majorByCollegeId;
	}

	@Override
	public XDormResult addMajor(TbMajor major) {
		try {
			String majorName = major.getMajorName();
			TbMajorExample example = new TbMajorExample();
			Criteria criteria = example.createCriteria();
			criteria.andMajorNameEqualTo(majorName);
			List<TbMajor> majorList = majorMapper.selectByExample(example);
			if (majorList.size() >= 1) {
				return XDormResult.build(400, "添加专业失败：该专业已存在");
			}
			int code = majorMapper.insert(major);
			if (code != 0) {
				return XDormResult.build(200, "添加学院成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return XDormResult.build(400, "添加学院失败-错误代码：addmajor");
	}

	@Override
	public XDormResult delMajorById(long majorId) {
		int code = majorMapper.deleteByPrimaryKey(majorId);
		if (code != 0) {
			return XDormResult.build(200, "删除专业成功");
		}
		return XDormResult.build(400, "删除专业失败");
	}

	@Override
	public XDormResult updateMajorById(TbMajor major) {

		try {
			// 检查该专业名称是否已经存在  可能只修改了学院
//			TbMajorExample example = new TbMajorExample();
//			Criteria criteria = example.createCriteria();
//			criteria.andMajorNameEqualTo(majorName);
//			List<TbMajor> majorList = majorMapper.selectByExample(example);
//			if (majorList.size() >= 1) {
//				return XDormResult.build(400, "该专业名称已存在");
//			}
			int code = majorMapper.updateByPrimaryKey(major);
			if (code != 0) {
				return XDormResult.build(200, "更新专业成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return XDormResult.build(400, "更新专业失败：错误代码-updatemajor");
	}

}
