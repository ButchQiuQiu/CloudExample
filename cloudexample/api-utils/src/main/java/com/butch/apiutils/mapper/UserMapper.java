package com.butch.apiutils.mapper;

import java.util.List;
import java.util.Map;

import com.butch.apiutils.pojo.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface UserMapper {
    /**
     * SpringSecurity用来验证用户的方法,使用用户名匹配用户然后框架判断密码
     * @param name 用户名
     * @return 一个用户
     */
	public User getUserByUsername(String name);
	
	/**
	 * 获取所有用户
	 * @return 用户合集
	 */
	public List<User> getUsersByUser(User user);

	/**
	 * 使用Map<String,User>删除一个用户
	 * @param users 装着2个用户的Map,
	 * 1. key值:myUser      当前登录的用户
	 * 2. key值:targetUser	想要操作的用户
	 * @return 处理结果,false:没有权限,true:操作成功
	 */
    public boolean deleteUserByUserMap(Map<String,User> users);

	/**
	 * 使用map<String,Object>修改一个用户
	 * 1. key值:myUser      当前登录的用户
	 * 2. key值:targetUser	想要操作的用户
	 * 3. key:column 需要修改的列
	 * 4. key:value  需要修改的值
	 * @param userMap
	 * @return
	 */
	public boolean updateUserByUserMap(Map<String, Object> userMap);

	/**
	 * 使用Map<String,User>添加一个用户
	 * @param users 装着2个用户的Map,
	 * 1. key值:myUser      当前登录的用户
	 * 2. key值:targetUser	想要操作的用户
	 * @return 处理结果,false:没有权限,true:操作成功
	 */
	public boolean insertUserByUsers(Map<String, User> users);
}