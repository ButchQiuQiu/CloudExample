package com.butch.apiutils.mapper;

import com.butch.apiutils.pojo.Authority;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * AuthorityMapper
 */
@Repository
@Mapper
public interface AuthorityMapper {
    public Authority getAuthorityById(int id);
}