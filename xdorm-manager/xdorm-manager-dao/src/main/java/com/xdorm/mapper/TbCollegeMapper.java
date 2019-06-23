package com.xdorm.mapper;

import com.xdorm.pojo.TbCollege;
import com.xdorm.pojo.TbCollegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbCollegeMapper {
    int countByExample(TbCollegeExample example);

    int deleteByExample(TbCollegeExample example);

    int deleteByPrimaryKey(Long collegeId);

    int insert(TbCollege record);

    int insertSelective(TbCollege record);

    List<TbCollege> selectByExample(TbCollegeExample example);

    TbCollege selectByPrimaryKey(Long collegeId);

    int updateByExampleSelective(@Param("record") TbCollege record, @Param("example") TbCollegeExample example);

    int updateByExample(@Param("record") TbCollege record, @Param("example") TbCollegeExample example);

    int updateByPrimaryKeySelective(TbCollege record);

    int updateByPrimaryKey(TbCollege record);
}