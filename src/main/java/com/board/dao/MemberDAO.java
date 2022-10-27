package com.board.dao;

import java.util.Map;

import com.board.domain.MemberDTO;

public interface MemberDAO {
	public Integer checkAvailability(Map<String, String> map);
	public Integer regist(MemberDTO dto);
	
}
