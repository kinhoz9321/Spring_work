package com.gura.spring05.cafe.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.cafe.dto.CafeDto;
import com.gura.spring05.cafe.service.CafeService;

@Controller
public class CafeController {
	//의존객체 DI
	@Autowired
	private CafeService service;
	
	@RequestMapping("/cafe/detail")
	public ModelAndView detail(@RequestParam int num, ModelAndView mView) {
		//자세히 보여줄 글 번호가 파라미터로 넘어온다.
		service.getDetail(num, mView);
		//view page 로 forward 이동해서 응답
		mView.setViewName("cafe/detail");
		return mView;
	}
	
	//글 목록 요청처리
	@RequestMapping("/cafe/list")
	public ModelAndView list(ModelAndView mView, HttpServletRequest request) {
		service.getList(mView, request);
		
		mView.setViewName("cafe/list");
		return mView;
	}
	
	//카페 새글 저장 요청 처리 (form 전송은 POST 로 받기 method = RequestMethod.POST 체크)
	@RequestMapping(value = "/cafe/private/insert", method = RequestMethod.POST) //POST 전송만 받는다.
	public String insert(CafeDto dto, HttpSession session) {
		//글 작성자는 세션에서 얻어내서 
		String id=(String)session.getAttribute("id");
		//dto 에 담는다.
		dto.setWriter(id);
		//서비스를 통해서 새글을 DB 에 저장
		service.saveContent(dto); //서비스임플 메소드 명
		
		return "cafe/private/insert";
	}
	
	@RequestMapping("/cafe/private/insertform") //GET,POST 둘 다 받는다.
	public String insertform() {
		
		// /WEB-INF/views/	cafe/private/insertform	.jsp 로 forward 이동해서 응답하겠다.
		return "cafe/private/insertform";
	}
}
