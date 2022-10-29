package com.board.service;

import com.board.domain.MemberDTO;

public interface MemberService {
	public Integer checkAvailability(String field, String value);
	public int certifyEmail(String email);
	public Integer regist(MemberDTO dto);
	public MemberDTO selectById(String id);
}
