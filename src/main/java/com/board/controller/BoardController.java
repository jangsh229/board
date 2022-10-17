package com.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String list(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		Criteria cri = new Criteria(Integer.parseInt(pg), 10); // 페이지 범위 객체 생성 (현재 페이지, 페이지에 보여줄 데이터 갯수)
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri, 5, service.getTotal())); // (cri, 페이지 버튼 갯수, 데이터 갯수)
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
	public String view(String seq, Model model) {
		BoardDTO dto = service.detail(Integer.parseInt(seq));
		model.addAttribute("detail", dto);
		return "/board/detail";
	}

	@RequestMapping(value = "/goUpdate", method = RequestMethod.POST)
	public String updateView(Model model, HttpServletRequest request) {
		BoardDTO dto = service.detail(Integer.parseInt(request.getParameter("seq")));
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
