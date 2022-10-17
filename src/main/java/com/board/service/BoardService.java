package com.board.service;

import java.util.List;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;

public interface BoardService {
	public List<BoardDTO> getList(Criteria cri);
	public int write(BoardDTO dto);
	public BoardDTO detail(int seq);
	public int update(BoardDTO dto);
	public int delete(int seq);
	public int getTotal();
}
