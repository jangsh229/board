package com.board.dao;

import java.util.List;
import java.util.Map;

import com.board.domain.BoardDTO;

public interface BoardDAO {
	public List<BoardDTO> getList(Map<String, Integer> map);
	public int write(BoardDTO dto);
	public BoardDTO detail(int seq);
	public void updateReadCount(int seq);
	public int update(BoardDTO dto);
	public int delete(int seq);
	public int getTotal();
}
