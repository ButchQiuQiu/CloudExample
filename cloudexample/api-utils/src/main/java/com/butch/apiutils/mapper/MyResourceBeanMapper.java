package com.butch.apiutils.mapper;

/**
 * 资源类Mapper
 */

import java.util.List;

import com.butch.apiutils.pojo.MyResourceBean;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface MyResourceBeanMapper {
    public List<MyResourceBean> selectAllResource();
}