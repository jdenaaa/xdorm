package com.xdorm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdorm.mapper.TbRoomMapper;
import com.xdorm.pojo.TbRoom;
import com.xdorm.pojo.TbRoomExample;
import com.xdorm.pojo.TbRoomExample.Criteria;
import com.xdorm.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private TbRoomMapper roomMapper;

	@Override
	public List<TbRoom> getRoomByDormIdAndFloor(long dormId, int floor) {
		TbRoomExample example = new TbRoomExample();
		Criteria criteria = example.createCriteria();
		criteria.andDormIdEqualTo(dormId);
		criteria.andFloorEqualTo(floor);
		List<TbRoom> roomList = roomMapper.selectByExample(example);
		return roomList;
	}

	@Override
	public TbRoom getRoomById(long roomid) {
		TbRoom room = roomMapper.selectByPrimaryKey(roomid);
		return room;
	}

}
