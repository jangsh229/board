package com.board.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.BoardDTO;
import com.board.domain.Criteria;
import com.board.domain.MemberDTO;
import com.board.domain.PageDTO;
import com.board.service.BoardService;
import com.board.service.MemberService;
import com.board.service.ReplyService;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Criteria cri, Model model) {
		model.addAttribute("list", boardService.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, boardService.getTotal(cri)));
		return "board/list";
	}

	@RequestMapping(value = "/goWrite", method = RequestMethod.GET)
	public String regiView(Principal login, Model model) {
		model.addAttribute("member", memberService.selectById(login.getName()));
		return "board/write";
	}

	@ResponseBody
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String regi(BoardDTO dto, Principal login) {
		MemberDTO writer = memberService.selectById(login.getName());
		dto.setMem_seq(writer.getMem_seq());
		if (boardService.write(dto) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String view(String seq, Criteria cri, Principal login,
						HttpServletRequest request, HttpServletResponse response, Model model) {
		BoardDTO boardDTO = boardService.detail(Integer.parseInt(seq), request, response);
		MemberDTO loginUser = null;
		if(login != null) {
			loginUser = memberService.selectById(login.getName());
		}
		model.addAttribute("detail", boardDTO);
		model.addAttribute("reply", replyService.list(Integer.parseInt(seq)));
		model.addAttribute("login", loginUser);
		model.addAttribute("isWriter", boardService.isWriter(boardDTO, loginUser));
		model.addAttribute("cri", cri);
		model.addAttribute("list", boardService.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, boardService.getTotal(cri)));
		return "board/detail";
	}

	@RequestMapping(value = "/goUpdate", method = RequestMethod.POST)
	public String updateView(String seq, Criteria cri, 
								HttpServletRequest request, HttpServletResponse response, Model model) {
		BoardDTO dto = boardService.detail(Integer.parseInt(seq), request, response);
		model.addAttribute("detail", dto);
		model.addAttribute("cri", cri);
		return "board/update";
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardDTO dto, Criteria cri, Model model) {
		model.addAttribute("cri", cri);
		if (boardService.update(dto) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		if (boardService.delete(seq) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}

}
