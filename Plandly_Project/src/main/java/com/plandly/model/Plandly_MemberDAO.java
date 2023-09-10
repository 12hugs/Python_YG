package com.plandly.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.plandly.db.SqlsessionManager;

public class Plandly_MemberDAO {
	
	SqlSessionFactory Factory = SqlsessionManager.getSqlSession();
	
	public int join(Plandly_MemberVO vo) { // 회원가입
		SqlSession sqlSession = Factory.openSession(true);
		int cnt = sqlSession.insert("com.plandly.db.PlandlyMemberMapper.join",vo);
		sqlSession.close();
		return cnt;
	}
	
	public int login(Plandly_MemberVO vo) {
		SqlSession sqlSession = Factory.openSession(true);
		int cnt = sqlSession.selectOne("com.plandly.db.PlandlyMemberMapper.login",vo);
		sqlSession.close();
		return cnt;
	}
	
	public List<Plandly_MemberVO> memberSession(Plandly_MemberVO vo){
	    SqlSession sqlSession = Factory.openSession(true);
	    List<Plandly_MemberVO> result = sqlSession.selectList("com.plandly.db.PlandlyMemberMapper.memberSession", vo);
	    sqlSession.close();
	    
	    return result;
	}
}
