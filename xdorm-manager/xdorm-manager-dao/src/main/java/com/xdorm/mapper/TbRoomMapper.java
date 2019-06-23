package com.xdorm.mapper;

import com.xdorm.pojo.TbRoom;
import com.xdorm.pojo.TbRoomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbRoomMapper {
    int countByExample(TbRoomExample example);

    int deleteByExample(TbRoomExample example);

    int deleteByPrimaryKey(Long roomId);

    int insert(TbRoom record);

    int insertSelective(TbRoom record);

    List<TbRoom> selectByExample(TbRoomExample example);

    TbRoom selectByPrimaryKey(Long roomId);

    int updateByExampleSelective(@Param("record") TbRoom record, @Param("example") TbRoomExample example);

    int updateByExample(@Param("record") TbRoom record, @Param("example") TbRoomExample example);

    int updateByPrimaryKeySelective(TbRoom record);

    int updateByPrimaryKey(TbRoom record);
}