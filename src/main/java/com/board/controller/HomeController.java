package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.service.BoardService;

@Controller
public class HomeController {
	@Autowired
	private BoardController controller;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		return controller.list(pg, model);
	}
	
}
