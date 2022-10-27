package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
