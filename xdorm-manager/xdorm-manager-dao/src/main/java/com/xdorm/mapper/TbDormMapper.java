package com.xdorm.mapper;

import com.xdorm.pojo.TbDorm;
import com.xdorm.pojo.TbDormExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbDormMapper {
    int countByExample(TbDormExample example);

    int deleteByExample(TbDormExample example);

    int deleteByPrimaryKey(Long dormId);

    int insert(TbDorm record);

    int insertSelective(TbDorm record);

    List<TbDorm> selectByExample(TbDormExample example);

    TbDorm selectByPrimaryKey(Long dormId);

    int updateByExampleSelective(@Param("record") TbDorm record, @Param("example") TbDormExample example);

    int updateByExample(@Param("record") TbDorm record, @Param("example") TbDormExample example);

    int updateByPrimaryKeySelective(TbDorm record);

    int updateByPrimaryKey(TbDorm record);
}