package com.xdorm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdorm.common.utils.JsonUtils;
import com.xdorm.pojo.TbRoom;
import com.xdorm.service.RoomService;

@Controller
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	/**
	 * 返回 某公寓 某层楼的房间列表信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/room/{dormId}/{floor}")
	@ResponseBody
	public String getRoomByDormIdAndFloor(@PathVariable Long dormId, @PathVariable Integer floor){
		List<TbRoom> room = roomService.getRoomByDormIdAndFloor(dormId, floor);
		String roomToJson = JsonUtils.objectToJson(room);
		return roomToJson;
	}
	
//	@RequestMapping("/room/roomid/{roomId}")
//	@ResponseBody
//	public TbRoom getRoomById(@PathVariable Long roomId) {
//		TbRoom room = roomService.getRoomById(roomId);
//		return room;
//	}
}
