package com.xdorm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdorm.common.pojo.StudentPageResult;
import com.xdorm.common.pojo.XDormResult;
import com.xdorm.mapper.TbRoomMapper;
import com.xdorm.mapper.TbStudentMapper;
import com.xdorm.pojo.TbRoom;
import com.xdorm.pojo.TbStudent;
import com.xdorm.pojo.TbStudentExample;
import com.xdorm.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private TbStudentMapper studentMapper;
	@Autowired
	private TbRoomMapper roomMapper;

	@Override
	public StudentPageResult getStudentList(int page) {
		// 设置分页信息
		PageHelper.startPage(page, 10);
		// 执行查询
		TbStudentExample example = new TbStudentExample();
		List<TbStudent> list = studentMapper.selectByExample(example);
		// 取查询结果
		PageInfo<TbStudent> pageInfo = new PageInfo<TbStudent>(list);
		StudentPageResult result = new StudentPageResult();
		result.setList(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * 根据Id删除学生
	 */
	@Override
	public XDormResult delStudentById(String stuId) {
		try {
			TbStudent student = studentMapper.selectByPrimaryKey(stuId);
			Long roomId = student.getRoomId();

			TbRoom room = roomMapper.selectByPrimaryKey(roomId);
			if (room.getMemberTotal() > 0) { // 防止 memberTotal 出现负数
				room.setMemberTotal(room.getMemberTotal() - 1);
				roomMapper.updateByPrimaryKey(room);
			}

			// 删除操作
			int code = studentMapper.deleteByPrimaryKey(stuId);
			if (code == 1) {
				// 返回成功
				return XDormResult.build(200, "删除成功");
			}
		} catch (Exception e) {
			return XDormResult.build(400, "删除失败：系统错误-delstudent");
		}

		// 返回失败
		return XDormResult.build(400, "删除失败");
	}

	@Override
	public XDormResult delStudentByIdList(List<String> stuIdList) {
		try {
			for (String stuId : stuIdList) {
				TbStudent student = studentMapper.selectByPrimaryKey(stuId);
				// 执行room member 减操作
				Long roomId = student.getRoomId();
				TbRoom room = roomMapper.selectByPrimaryKey(roomId);
				if (room.getMemberTotal() > 0) { // 防止 memberTotal 出现负数
					room.setMemberTotal(room.getMemberTotal() - 1);
					roomMapper.updateByPrimaryKey(room);
				}
			}
			int code = studentMapper.deleteStudentByStuIdList(stuIdList);
			if (code != 0) {
				return XDormResult.build(200, "删除了" + code + "条记录");
			}
		} catch (Exception e) {
			return XDormResult.build(400, "删除失败：系统错误-delstudentlist");
		}
		return XDormResult.build(400, "删除失败");
	}

	/**
	 * 添加学生
	 */
	@Override
	public XDormResult addStudent(TbStudent student) {
		try {
			// 检查安排的房间是否还有空床位
			Long roomId = student.getRoomId();
			TbRoom room = roomMapper.selectByPrimaryKey(roomId);
			Integer bedCount = room.getBedCount();
			Integer memberTotal = room.getMemberTotal();
			// 检查床位是否小于已住人数
			if (bedCount >= memberTotal) {
				int code = studentMapper.insert(student);
				if (code != 0) {
					// 更新该房间已住人数 +1
					room.setMemberTotal(room.getMemberTotal() + 1);
					roomMapper.updateByPrimaryKey(room);
					// 返回成功
					return XDormResult.build(200, "添加成功");
				}
			} else {
				return XDormResult.build(400, "该房间已满员");
			}

		} catch (Exception e) {
			return XDormResult.build(400, "意外错误，请检查 <学号> 是否已存在");
		}
		return XDormResult.build(400, "添加失败");

	}

	@Override
	public TbStudent getStudentById(String stuId) {
		TbStudent student = studentMapper.selectByPrimaryKey(stuId);
		return student;
	}

	/**
	 * 更新学生 住宿信息： 根据stuid查询学生 的 roomId,由roomId查询room,对应房间的 memberTotal -1 ， 获取新提交的
	 * roomId 查询对应房间 memberTotal +1
	 */
	@Override
	public XDormResult updateStudentById(TbStudent newStudent) {
		// 根据stuID 获取学生在数据库中的 旧的 roomId
		String stuId = newStudent.getStuId();
		// 旧student对象
		TbStudent oldStudent = studentMapper.selectByPrimaryKey(stuId);
		// 获取旧对象的 roomId
		Long oldRoomId = oldStudent.getRoomId();
		// 获取新 student 对象的 roomId
		Long newRoomId = newStudent.getRoomId();
		// 检查是否修改了房间
		if (newRoomId == oldRoomId) {
			return XDormResult.build(400, "没有修改房间信息");
		}

		try {
			// 执行更新操作
			// 获取 旧 room 对象 更新 MemberTottal - 1
			TbRoom oldRoom = roomMapper.selectByPrimaryKey(oldRoomId);
			if (oldRoom.getMemberTotal() > 0) { // 防止 memberTotal 出现负数
				oldRoom.setMemberTotal(oldRoom.getMemberTotal() - 1);
				roomMapper.updateByPrimaryKey(oldRoom);
			}

			// 获取 新 room 对象 更新 memberTotal + 1
			TbRoom newRoom = roomMapper.selectByPrimaryKey(newRoomId);
			if (newRoom.getMemberTotal() >= newRoom.getBedCount()) {
				return XDormResult.build(400, "该房间已满员，请重新选择");
			}
			newRoom.setMemberTotal(newRoom.getMemberTotal() + 1);
			roomMapper.updateByPrimaryKey(newRoom);

			// 更新学生信息
			int code = studentMapper.updateByPrimaryKey(newStudent);
			if (code != 0) {
				return XDormResult.build(200, "更新学生信息成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return XDormResult.build(400, "系统异常：错误代码-updatestudent");
	}

}
