package com.xdorm.mapper;

import com.xdorm.pojo.TbMajor;
import com.xdorm.pojo.TbMajorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbMajorMapper {
    int countByExample(TbMajorExample example);

    int deleteByExample(TbMajorExample example);

    int deleteByPrimaryKey(Long majorId);

    int insert(TbMajor record);

    int insertSelective(TbMajor record);

    List<TbMajor> selectByExample(TbMajorExample example);

    TbMajor selectByPrimaryKey(Long majorId);

    int updateByExampleSelective(@Param("record") TbMajor record, @Param("example") TbMajorExample example);

    int updateByExample(@Param("record") TbMajor record, @Param("example") TbMajorExample example);

    int updateByPrimaryKeySelective(TbMajor record);

    int updateByPrimaryKey(TbMajor record);
}