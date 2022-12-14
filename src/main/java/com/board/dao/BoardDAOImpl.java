package com.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	private static String namespace = "boardSql"; //mapper의 namespace와 동일해야 함
	
	@Override
	public List<BoardDTO> getList(Criteria cri) {
		return sqlSession.selectList(namespace+".getList", cri);
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
	public int getTotal(Criteria cri) {
		return sqlSession.selectOne(namespace+".getTotal", cri);
	}
	
	@Override
	public int updateReplyCount(int board_seq) {
		return sqlSession.update(namespace+".updateReplyCount", board_seq);
	}
}
