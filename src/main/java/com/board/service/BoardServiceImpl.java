package com.board.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.BoardDAO;
import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.MemberDTO;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO dao;

	@Override
	public List<BoardDTO> getList(Criteria cri) {
		return dao.getList(cri);
	}

	@Override
	public int write(BoardDTO dto) {
		return dao.write(dto);
	}

	@Override
	public BoardDTO detail(int seq, HttpServletRequest request, HttpServletResponse response) {
		// 조회수 증가 방지용 쿠키 생성여부 확인
		Cookie[] cookies = request.getCookies();
		Cookie viewCookie = null;

		// view 쿠키가 이미 있을 때
		for (Cookie c : cookies) {
			if (c.getName().equals("view")) {
				viewCookie = c;
				String cookieValue = c.getValue();

				// 해당 글의 조회내역이 없을 경우
				if (!cookieValue.contains(seq + "")) {
					cookieValue += seq + "/";
					response.addCookie(new Cookie("view", cookieValue));
					dao.updateReadCount(seq); // 조회수 증가
				}
			}
		}

		// view 쿠키가 없을 때
		if (viewCookie == null) {
			Cookie newCookie = new Cookie("view", seq + "/");
			newCookie.setMaxAge(60 * 60);
			response.addCookie(newCookie);
			dao.updateReadCount(seq); // 조회수 증가
		}

		return dao.detail(seq);
	}

	@Override
	public int update(BoardDTO dto) {
		return dao.update(dto);
	}

	@Override
	public int delete(int seq) {
		return dao.delete(seq);
	}
	
	@Override
	public int getTotal(Criteria cri) {
		return dao.getTotal(cri);
	}
	
	@Override
	public boolean isWriter(BoardDTO boardDTO, MemberDTO loginUser) {
		if(loginUser == null) {
			return false;
		} else {
			if(boardDTO.getMem_seq() == loginUser.getMem_seq()) {
				return true;
			} else {
				return false;
			}
		}
	}
}
