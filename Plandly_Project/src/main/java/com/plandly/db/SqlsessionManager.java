package com.plandly.db;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlsessionManager {

	public static SqlSessionFactory sqlSessionFactory; // mybatis API가 지원해주는 Factory임
	
	public static SqlSessionFactory getSqlSession() {
		return sqlSessionFactory;
	}

	static {
		try {
			String resource = "com/plandly/db/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			e.printStackTrace(); // 에러 메시지 출력
	        throw new RuntimeException("MyBatis 초기화 실패", e); // 초기화 실패 시 프로그램 종료
		}

	}
}

