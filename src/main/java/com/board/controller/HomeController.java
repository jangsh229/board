package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.board.domain.Criteria;

@Controller
public class HomeController {
	@Autowired
	private BoardController controller;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String list(Criteria cri, Model model) {
		return controller.list(new Criteria(), model);
	}
	
}
