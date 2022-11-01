package com.board.dao;

import java.util.List;

import com.board.domain.ReplyDTO;

public interface ReplyDAO {
	public List<ReplyDTO> list(int boardSeq);
	public Integer regist(ReplyDTO dto);
	public ReplyDTO selectBySeq(int rep_seq);
	public int update(ReplyDTO dto);

}
