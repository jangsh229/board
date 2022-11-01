package com.board.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.MemberDTO;
import com.board.domain.ReplyDTO;
import com.board.service.MemberService;
import com.board.service.ReplyService;

@Controller
@RequestMapping(value = "/reply/*")
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<ReplyDTO> list(int boardSeq) {
		return replyService.list(boardSeq);
	}
	
	@ResponseBody
	@RequestMapping(value = "/regi", method = RequestMethod.POST)
	public String regi(ReplyDTO dto, Principal login) {
		MemberDTO writer = memberService.selectById(login.getName());
		dto.setMem_seq(writer.getMem_seq());
		if (replyService.regist(dto) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(String rep_seq) {
		ReplyDTO dto = replyService.selectBySeq(Integer.parseInt(rep_seq));
		if (replyService.delete(dto) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ReplyDTO dto) {
		if (replyService.update(dto) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public String refresh(int boardSeq, Principal login, Model model) {
		MemberDTO loginUser = null;
		if(login != null) {
			loginUser = memberService.selectById(login.getName());
		}
		model.addAttribute("reply", replyService.list(boardSeq));
		model.addAttribute("login", loginUser);
		return "";
	}
}
