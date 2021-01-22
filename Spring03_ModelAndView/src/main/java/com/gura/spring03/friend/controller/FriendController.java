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
	 * ModelAndView 객체에 담긴 data는 자동으로 request scope 에 담기고 ***
	 * ModelAndView 객체에 담긴 view page 정보대로 forward 이동된다. ***
	 */
	
	@RequestMapping("/friend/list2")
	public ModelAndView list2() {//return mView;
		//view page 에 전달할 Model(data)
		List<String> list=new ArrayList<String>();
		list.add("김구라");
		list.add("해골");
		list.add("원숭이");
		
		//1. ModelAndView 객체를 생성해서 (자주 사용할 것)
		ModelAndView mView=new ModelAndView();
		
		//2. Model(data) 를 담고
		mView.addObject("list", list);
		
		//3. view page 정보도 담고
		mView.setViewName("friend/list");
		
		//4. 리턴해준다.
		return mView;
	}
	
	//메소드의 인자로 ModelAndView 를 선언하면 Spring 이 객체를 생성해서 전달해 준다.
	@RequestMapping("/friend/list3")
	public ModelAndView list3(ModelAndView mView) {
		//view page 에 전달할 Model(data)
		List<String> list=new ArrayList<String>();
		list.add("김구라");
		list.add("해골");
		list.add("원숭이");
		
		//ModelAndView 객체를 직접 생성하지 않고 메소드의 인자로 전달 받아서 사용할 수도 있다.
		mView.addObject("list", list);
		mView.setViewName("friend/list");
		return mView;
	}
	
	@RequestMapping("/friend/delete")
	public String delete(HttpServletRequest request) {
		//삭제할 번호
		int num=Integer.parseInt(request.getParameter("num"));
		System.out.println(num+" 번 친구의 정보를 삭제 했습니다.");
		
		/*
		 * [ 리다일렉트 이동 ]
		 * 웹브라우저에게 새로운 경로로 요청을 다시 하라고 응답하는게 리다일렉트 이동이다.
		 * 스프링에서 리다일렉트 응답을 할 때는 view page 정보를 
		 * "redirect: *컨텍스트 경로를 제외*한 절대 경로" 와 같이 작성하면 된다.
		 * 
		 * ModelAndView 객체도 같다
		 * mView.setViewName("redirect: 경로");
		 */
		
		//친구 목록 보기로 리다일렉트 이동 시키기
		return "redirect:/friend/list.do";
	}
	
	/*
	 * 여기서 확인할 것
	 * 리다일렉트 이동 *
	 * 친구 목록 삭제하기1,2를 누르면
	 * http://localhost:8888/spring03/friend/list.do 으로 이동된다.
	 * 
	 * 콘솔창에 출력 (GET 방식 전송 확인) *
	 * 1번 친구의 정보를 삭제 했습니다.
	 * 2번 친구의 정보를 삭제 했습니다.
	 */
}
