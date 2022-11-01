package com.board.service;

import java.util.List;

import com.board.domain.ReplyDTO;

public interface ReplyService {
	public List<ReplyDTO> list(int boardSeq);
	public Integer regist(ReplyDTO dto);
	public ReplyDTO selectBySeq(int rep_seq);
	public int delete(ReplyDTO dto);
	public int update(ReplyDTO dto);
}
