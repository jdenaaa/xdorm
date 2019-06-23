package com.xdorm.mapper;

import com.xdorm.pojo.TbClass;
import com.xdorm.pojo.TbClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbClassMapper {
    int countByExample(TbClassExample example);

    int deleteByExample(TbClassExample example);

    int deleteByPrimaryKey(Long classId);

    int insert(TbClass record);

    int insertSelective(TbClass record);

    List<TbClass> selectByExample(TbClassExample example);

    TbClass selectByPrimaryKey(Long classId);

    int updateByExampleSelective(@Param("record") TbClass record, @Param("example") TbClassExample example);

    int updateByExample(@Param("record") TbClass record, @Param("example") TbClassExample example);

    int updateByPrimaryKeySelective(TbClass record);

    int updateByPrimaryKey(TbClass record);
}