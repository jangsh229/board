package com.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;

public interface BoardService {
	public List<BoardDTO> getList(Criteria cri);
	public int write(BoardDTO dto);
	public BoardDTO detail(int seq, HttpServletRequest request, HttpServletResponse response);
	public int update(BoardDTO dto);
	public int delete(int seq);
	public int getTotal(Criteria cri);
}
