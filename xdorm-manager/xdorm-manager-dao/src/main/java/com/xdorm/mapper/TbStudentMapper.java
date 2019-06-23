package com.xdorm.mapper;

import com.xdorm.pojo.TbStudent;
import com.xdorm.pojo.TbStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbStudentMapper {
    int countByExample(TbStudentExample example);

    int deleteByExample(TbStudentExample example);

    int deleteByPrimaryKey(String stuId);

    int insert(TbStudent record);

    int insertSelective(TbStudent record);

    List<TbStudent> selectByExample(TbStudentExample example);

    TbStudent selectByPrimaryKey(String stuId);

    int updateByExampleSelective(@Param("record") TbStudent record, @Param("example") TbStudentExample example);

    int updateByExample(@Param("record") TbStudent record, @Param("example") TbStudentExample example);

    int updateByPrimaryKeySelective(TbStudent record);

    int updateByPrimaryKey(TbStudent record);
    
    /**
     * 自定义的 根据 idList 批量删除 返回删除的 记录数
     * @param stuIdList
     * @return
     */
    int deleteStudentByStuIdList(List<String> stuIdList);
}