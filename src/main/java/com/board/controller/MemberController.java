package com.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.MemberDTO;
import com.board.service.MemberService;

@Controller
@RequestMapping(value = "/member/*")
public class MemberController {
	@Autowired
	private MemberService service;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "noTiles/member/signup";
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkAvailability", method = RequestMethod.GET)
	public Boolean checkAvailability(String field, String value) {
		if(service.checkAvailability(field, value) == 1) {
			return false;
		} else {
			return true;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/certifyEmail", method = RequestMethod.GET)
	public int certifyEmail(String email) {
		return service.certifyEmail(email);
	}
	
	@ResponseBody
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public Boolean regist(MemberDTO dto) {
		if(service.regist(dto) == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false)String error,
						@RequestParam(value = "exception", required = false)String exception,
						HttpServletRequest request,
						Model model) {
		model.addAttribute("error", error);
		model.addAttribute("exception", exception);
		// 로그인 성공 시 되돌아갈 이전 페이지 정보 저장
		String uri = request.getHeader("Referer");
		if(uri != null && !uri.contains("/login")) {
			request.getSession().setAttribute("prevPage", uri);
		}
		return "noTiles/member/login";
	}
}
