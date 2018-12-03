package com.yancy.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * mybatis工具类
 * 
 * @author yancy
 *
 */
public class SqlSessionFactoryUtil {
	private static SqlSessionFactory sqlSessionFactory = null;
	private static final Class CLASS_LOCK = SqlSessionFactoryUtil.class;

	private SqlSessionFactoryUtil() {
		// 私有化构造方法
	}

	public static SqlSessionFactory initSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			Logger.getLogger(SqlSessionFactoryUtil.class.getName()).log(Priority.DEBUG, null, e);
		}
		synchronized (CLASS_LOCK) {
			if (sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			}
		}
		return sqlSessionFactory;
	}

	public static SqlSession openSqlSession() {
		if (sqlSessionFactory == null) {
			initSqlSessionFactory();
		}
		return sqlSessionFactory.openSession();
	}

}
