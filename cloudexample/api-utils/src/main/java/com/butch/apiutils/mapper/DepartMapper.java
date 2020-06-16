package com.butch.apiutils.mapper;

import java.util.List;

import com.butch.apiutils.pojo.Depart;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface DepartMapper {
    /**
     * 查询所有的Depart
     * @return 所有的Depart
     */
    public List<Depart> getAllDepart();

    /**
     * 使用一个id获取对应的Depart
     * @param id
     * @return 对应的Depart
     */
    public Depart getDepartById(int id);
}