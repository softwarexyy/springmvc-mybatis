package com.yancy.test;

import org.apache.ibatis.session.SqlSession;

import com.yancy.mapper.UserMapper;
import com.yancy.entity.User;
import com.yancy.util.SqlSessionFactoryUtil;

/**
 * �����࣬��֤mybatis
 * 
 * @author yancy
 *
 */
public class Test {

	public static void main(String[] args) {
		SqlSession sqlSession = null;
		sqlSession = SqlSessionFactoryUtil.openSqlSession();
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

		// ����getUser����
		User user = userMapper.getUser((int) 2);
		System.out.println("-------> test getUser: " + user + " <----------");

		// ����addUser����
		User userToAdd = new User();
		// TODO:�������һ��ID
		userToAdd.setId(3); // ID
		userToAdd.setName("yancy");
		userToAdd.setPasswd("123");
		userMapper.addUser(userToAdd);
		System.out.println("-------> test addUser: " + userToAdd + " <----------");
		sqlSession.commit();

		// ����deleteUser����
		userMapper.deleteUser(3);
		System.out.println("-------> test deleteUser <----------");
		sqlSession.commit();

		sqlSession.close();
	}
}
