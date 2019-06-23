package com.xdorm.mapper;

import com.xdorm.pojo.TbGrade;
import com.xdorm.pojo.TbGradeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbGradeMapper {
    int countByExample(TbGradeExample example);

    int deleteByExample(TbGradeExample example);

    int deleteByPrimaryKey(Long gradeId);

    int insert(TbGrade record);

    int insertSelective(TbGrade record);

    List<TbGrade> selectByExample(TbGradeExample example);

    TbGrade selectByPrimaryKey(Long gradeId);

    int updateByExampleSelective(@Param("record") TbGrade record, @Param("example") TbGradeExample example);

    int updateByExample(@Param("record") TbGrade record, @Param("example") TbGradeExample example);

    int updateByPrimaryKeySelective(TbGrade record);

    int updateByPrimaryKey(TbGrade record);
}