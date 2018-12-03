package com.yancy.mapper;

import com.yancy.entity.User;

/**
 * 数据库操作的接口
 * @author yancy
 *
 */
public interface UserMapper {
	public User getUser(int id);
	public int addUser(User user);
	public int deleteUser(int id);
	public User getUserByName(String name);
}
