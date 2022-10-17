package com.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	private static String namespace = "boardSql"; //mapper의 namespace와 동일해야 함
	
	@Override
	public List<BoardDTO> getList(Map<String, Integer> map) {
		return sqlSession.selectList(namespace+".getList", map);
	}
	
	@Override
	public int write(BoardDTO dto) {
		return sqlSession.insert(namespace+".write", dto);
	}
	
	@Override
	public void updateReadCount(int seq) {
		sqlSession.update(namespace+".updateReadCount", seq);
	}
	
	@Override
	public BoardDTO detail(int seq) {
		return sqlSession.selectOne(namespace+".detail", seq);
	}
	
	@Override
	public int update(BoardDTO dto) {
		return sqlSession.update(namespace+".update", dto);
	}
	
	@Override
	public int delete(int seq) {
		return sqlSession.delete(namespace+".delete", seq);
	}
	
	@Override
	public int getTotal() {
		return sqlSession.selectOne(namespace+".getTotal");
	}
}
