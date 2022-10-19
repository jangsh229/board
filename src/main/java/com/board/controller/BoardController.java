package com.board.controller;

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
import com.board.domain.PageDTO;
import com.board.service.BoardService;

@Controller
@RequestMapping(value = "/board/*")
public class BoardController {
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Criteria cri, Model model) {
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, service.getTotal(cri)));
		return "/board/list";
	}

	@RequestMapping(value = "/goWrite", method = RequestMethod.GET)
	public String regiView() {
		return "/board/write";
	}

	@ResponseBody
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String regi(BoardDTO dto) {
		if (service.write(dto) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String view(String seq, HttpServletRequest request, HttpServletResponse response, Model model) {
		BoardDTO dto = service.detail(Integer.parseInt(seq), request, response);
		model.addAttribute("detail", dto);
		return "/board/detail";
	}

	@RequestMapping(value = "/goUpdate", method = RequestMethod.POST)
	public String updateView(String seq, HttpServletRequest request, HttpServletResponse response, Model model) {
		BoardDTO dto = service.detail(Integer.parseInt(seq), request, response);
		model.addAttribute("detail", dto);
		return "/board/update";
	}

	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardDTO dto) {
		if (service.update(dto) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request) {
		int seq = Integer.parseInt(request.getParameter("seq"));
		if (service.delete(seq) == 1) {
			return "Y";
		} else {
			return "N";
		}
	}

}
