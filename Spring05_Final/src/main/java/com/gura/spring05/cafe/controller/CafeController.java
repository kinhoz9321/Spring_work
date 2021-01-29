package com.gura.spring05.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CafeController {
	
	@RequestMapping("/cafe/private/insertform")
	public String insertform() {
		
		// /WEB-INF/views/	cafe/private/insertform	.jsp 로 forward 이동해서 응답하겠다.
		return "cafe/private/insertform";
	}
}
