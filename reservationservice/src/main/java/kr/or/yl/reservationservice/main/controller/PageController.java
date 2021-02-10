package kr.or.yl.reservationservice.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {
	
	@GetMapping(value = "/")
	public String main() {
		return "mainpage";
	}

	@GetMapping("detail/{id}")
	public String detail(@PathVariable(name="id") int id, Model model) {
		model.addAttribute("displayInfoId", id);
		return "detail";
	}

	@GetMapping("review/{id}")
	public String review(@PathVariable(name="id") int id, Model model) {
		model.addAttribute("displayInfoId", id);
		return "review";
	}
}
