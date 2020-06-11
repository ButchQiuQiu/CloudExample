package com.butch.apiutils.mapper;

import java.util.List;

import com.butch.apiutils.pojo.Sex;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface SexMapper {
    public List<Sex> getAllSex();
    public Sex getSexById();
}