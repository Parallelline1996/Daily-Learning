package com.parallelline1996.dao;

import com.parallelline1996.bean.People;
import com.parallelline1996.bean.PeopleExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PeopleMapper {
    long countByExample(PeopleExample example);

    int deleteByExample(PeopleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(People record);

    int insertSelective(People record);

    List<People> selectByExample(PeopleExample example);

    People selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") People record, @Param("example") PeopleExample example);

    int updateByExample(@Param("record") People record, @Param("example") PeopleExample example);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);
}