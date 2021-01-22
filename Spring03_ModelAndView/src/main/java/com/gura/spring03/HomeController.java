package com.gura.spring03;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/home")//home.do 요청
	public String home(HttpServletRequest request) {
		//DB에서 불러온 공지 사항 목록이라고 가정하자
		List<String> noticelist=new ArrayList<String>();
		noticelist.add("HomeController");
		noticelist.add("에서 작성한");
		noticelist.add("공지사항을 home.jsp에서");
		noticelist.add("jstl el 사용해서 출력함");
		
		//Model(데이터) 를 request scope 에 담는다.
		request.setAttribute("noticelist", noticelist); //("key", type(List<String>)) 기억
		
		//view page 를 forward 이동해서 응답하기
		return "home";
	}
	
}
