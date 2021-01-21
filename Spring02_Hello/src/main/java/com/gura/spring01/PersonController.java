package com.gura.spring01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonController {
	
	@RequestMapping("/person.do")
	public String person(HttpServletRequest request) {
		String name="김홍주";
		request.setAttribute("name", name);
		return "person";
	}
}
