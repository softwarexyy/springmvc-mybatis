package com.yancy.test;

import org.apache.ibatis.session.SqlSession;

import com.yancy.mapper.UserMapper;
import com.yancy.entity.User;
import com.yancy.util.SqlSessionFactoryUtil;

/**
 * 测试mybatis
 * 
 * @author yancy
 *
 */
public class Test {

	public static void main(String[] args) {
		SqlSession sqlSession = null;
		sqlSession = SqlSessionFactoryUtil.openSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		// 测试getUser方法
		User user = userMapper.getUser((int) 2);
		System.out.println("-------> test getUser: " + user + " <----------");

		// 测试addUser方法
		User userToAdd = new User();
		userToAdd.setId(3); // ID
		userToAdd.setName("yancy");
		userToAdd.setPasswd("123");
		userMapper.addUser(userToAdd);
		System.out.println("-------> test addUser: " + userToAdd + " <----------");
		sqlSession.commit();

		// 测试deleteUser方法
		userMapper.deleteUser(3);
		System.out.println("-------> test deleteUser <----------");
		sqlSession.commit();

		sqlSession.close();
	}
}
