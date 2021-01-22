package com.gura.spring03.friend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FriendController {
	
	@RequestMapping("/friend/list.do") // /반드시 있어야 함.
	public String list(HttpServletRequest request) {
		//view page 에 전달할 Model(data)
		List<String> list=new ArrayList<String>();
		list.add("김구라");
		list.add("해골");
		list.add("원숭이");
		
		//request scope 에 "list" 라는 키값으로 Model 담기
		request.setAttribute("list", list);
		
		// /WEB-INF/views/friend/list.jsp
		return "friend/list"; // /없어야 함.
	}
	
	/*
	 * 더 많이 사용하는 방법 ***
	 * ModelAndView 객체는
	 * Model(data) 와 view page 정보를 동시에 담을 수 있는 객체이다.
	 * ModelAndView 객체를 컨트롤러의 메소드에서 리턴해주면
	 * ModelAndView 객체에 담긴 data는 자동으로 request scope 에 담기고
	 * ModelAndView 객체에 담긴 view page 정보대로 forward 이동된다.
	 */
	
	@RequestMapping("/friend/list2")
	public ModelAndView list2() {
		//view page 에 전달할 Model(data)
		List<String> list=new ArrayList<String>();
		list.add("김구라");
		list.add("해골");
		list.add("원숭이");
		
		//1. ModelAndView 객체를 생성해서
		ModelAndView mView=new ModelAndView();
		
		//2. Model(data) 를 담고
		mView.addObject("list", list);
		
		//3. view page 정보도 담고
		mView.setViewName("friend/list");
		
		//4. 리턴해준다.
		return mView;
	}
}
