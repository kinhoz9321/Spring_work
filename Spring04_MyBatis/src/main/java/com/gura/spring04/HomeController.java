package com.gura.spring04;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	
	@RequestMapping("/home")
	public String home() {
		
		return "home";
	}
	
}
/*
 * @RequestMapping("/home")
 * ("/home") 이걸 쓰지 않으면 주소창에 링크를 클릭할때마다
 * http://localhost:8888/spring04/member/member/member/list.do 이런식으로 오류가 난다.
 */