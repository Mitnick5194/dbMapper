package com.ajie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ajie.pojo.TbLabel;
import com.ajie.pojo.TbLabelExample;

public interface TbLabelMapper {
    int countByExample(TbLabelExample example);

    int deleteByExample(TbLabelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbLabel record);

    int insertSelective(TbLabel record);

    List<TbLabel> selectByExample(TbLabelExample example);

    TbLabel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbLabel record, @Param("example") TbLabelExample example);

    int updateByExample(@Param("record") TbLabel record, @Param("example") TbLabelExample example);

    int updateByPrimaryKeySelective(TbLabel record);

    int updateByPrimaryKey(TbLabel record);
}