package com.board.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.board.dao.BoardDAO;
import com.board.dao.ReplyDAO;
import com.board.domain.ReplyDTO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Autowired
	private ReplyDAO dao;
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<ReplyDTO> list(int boardSeq) {
		return dao.list(boardSeq);
	}
	
	@Transactional
	@Override
	public Integer regist(ReplyDTO dto) {
		boardDAO.updateReplyCount(dto.getBoard_seq());
		return dao.regist(dto);
	}
	
	@Override
	public ReplyDTO selectBySeq(int rep_seq) {
		return dao.selectBySeq(rep_seq);
	}
	
	@Override
	public int delete(ReplyDTO dto) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String sysdate = sdf.format(cal.getTime());
		String content = "삭제된 댓글입니다. <span style='color:#7C7C7C;'>(삭제일시: " + sysdate + ")</span>";
		dto.setRep_content(content);
		dto.setDeleted(1);
		return dao.update(dto);
	}
	
	@Override
	public int update(ReplyDTO dto) {
		dto.setDeleted(0);
		return dao.update(dto);
	}

}
