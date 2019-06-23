package com.xdorm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdorm.common.pojo.XDormResult;
import com.xdorm.mapper.TbClassMapper;
import com.xdorm.pojo.TbClass;
import com.xdorm.pojo.TbClassExample;
import com.xdorm.pojo.TbClassExample.Criteria;
import com.xdorm.service.ClassService;

@Service
public class ClassServiceImpl implements ClassService {

	@Autowired
	private TbClassMapper classMapper;

	@Override
	public List<TbClass> getAllClass() {
		TbClassExample example = new TbClassExample();
		List<TbClass> allClass = classMapper.selectByExample(example);
		return allClass;
	}

	@Override
	public List<TbClass> getClassByMajorId(long majorId) {
		TbClassExample example = new TbClassExample();
		Criteria criteria = example.createCriteria();
		criteria.andMajorIdEqualTo(majorId);
		List<TbClass> classByMajorid = classMapper.selectByExample(example);
		return classByMajorid;
	}

	@Override
	public List<TbClass> getClassByMajorIdAndGradeId(long majorId, long gradeId) {
		TbClassExample example = new TbClassExample();
		Criteria criteria = example.createCriteria();
		criteria.andMajorIdEqualTo(majorId);
		criteria.andGradeIdEqualTo(gradeId);
		List<TbClass> classByMidAndGid = classMapper.selectByExample(example);
		return classByMidAndGid;
	}

	@Override
	public XDormResult addClass(TbClass tbClass) {
		try {
			TbClassExample example = new TbClassExample();
			Criteria criteria = example.createCriteria();
			criteria.andClassNameEqualTo(tbClass.getClassName());
			List<TbClass> classList = classMapper.selectByExample(example);
			if (classList.size() >= 1) {
				return XDormResult.build(400, "添加失败：该班级名称已存在");
			}

			int code = classMapper.insert(tbClass);
			if (code != 0) {
				return XDormResult.build(200, "添加班级成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return XDormResult.build(400, "系统异常：错误代码 addclass");
	}

	@Override
	public XDormResult updateClassById(TbClass tbClass) {
		try {
			int code = classMapper.updateByPrimaryKey(tbClass);
			if (code != 0) {
				return XDormResult.build(200, "更新班级信息成功");
			}
			return XDormResult.build(400, "更新班级信息失败");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return XDormResult.build(400, "更新班级信息失败");
	}

	@Override
	public XDormResult delClassById(long classId) {
		try {
			int code = classMapper.deleteByPrimaryKey(classId);
			if(code >= 1) {
				return XDormResult.build(200, "删除班级成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return XDormResult.build(400, "删除班级失败：错误代码 delclass");
	}

}
