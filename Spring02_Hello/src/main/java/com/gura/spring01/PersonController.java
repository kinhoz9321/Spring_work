package com.gura.spring01;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* component scan 될 때 PersonController 객체가 생성이 되고 관리가 된다. */
@Controller //상속의 역할을 대신해줌
public class PersonController {
	
	@RequestMapping("/person.do")
	public String person(HttpServletRequest request) {
		//views page 에 전달할 data (Model)
		String name="김홍주";
		
		//request scope 에 담는다. (패턴 기억하기)
		request.setAttribute("name", name);
		
		//forward 이동할 view page 의 위치를 리턴해준다.
		return "person"; // /WEB-INF/views/person.jsp
	}
}
/*
	data = Model
 */