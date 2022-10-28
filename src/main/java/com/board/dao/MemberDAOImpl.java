package com.board.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Autowired
	private SqlSession sqlSession;
	private static String namespace = "memberSql";
	
	@Override
	public Integer checkAvailability(Map<String, String> map) {
		return sqlSession.selectOne(namespace+".checkAvailability", map);
	}
	
	@Override
	public Integer regist(MemberDTO dto) {
		return sqlSession.insert(namespace+".regist", dto);
	}
	
	@Override
	public MemberDTO selectById(String id) {
		return sqlSession.selectOne(namespace+".selectById" ,id);
	}
}
