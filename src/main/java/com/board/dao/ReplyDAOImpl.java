package com.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.ReplyDTO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Autowired
	private SqlSession sqlSession;
	private static String namespace = "replySql";
	
	@Override
	public List<ReplyDTO> list(int boardSeq) {
		return sqlSession.selectList(namespace+".list", boardSeq);
	}
	
	@Override
	public Integer regist(ReplyDTO dto) {
		return sqlSession.insert(namespace+".regist", dto);
	}
	
	@Override
	public ReplyDTO selectBySeq(int rep_seq) {
		return sqlSession.selectOne(namespace+".selectBySeq", rep_seq);
	}
	
	@Override
	public int update(ReplyDTO dto) {
		return sqlSession.update(namespace+".update", dto);
	}
}
