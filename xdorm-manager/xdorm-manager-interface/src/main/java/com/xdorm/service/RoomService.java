package com.xdorm.service;

import java.util.List;

import com.xdorm.pojo.TbRoom;

public interface RoomService {
	
	/**
	 * 根据 roomid 返回房间信息
	 * @param roomid
	 * @return
	 */
	TbRoom getRoomById(long roomid);
	
	/**
	 * 根据dormId和floor(层)获取房间
	 * @param dormId
	 * @param floor
	 * @return
	 */
	List<TbRoom> getRoomByDormIdAndFloor(long dormId,int floor);
}
