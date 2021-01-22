package com.gura.spring03.friend.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring03.friend.dto.FriendDto;

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
	
	//친구 추가 폼 요청 처리
	@RequestMapping("/friend/insertform")
	public String insertform() {
		//수행할 비즈니스 로직은 없고 단순히 view page 정보만 리턴하는 경우도 있다.
		return "friend/insertform";
	}
	
	//친구 추가 요청 처리
	/*폼 전송을 추출하는 세가지 방법 1*/
	@RequestMapping("/friend/insert")
	public String insert(HttpServletRequest request) {
	
		//폼 전송되는 파라미터 추출
		int num=Integer.parseInt(request.getParameter("num"));
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		
		//추출된 정보 테스트로 출력해보기
		System.out.println(num+"|"+name+"|"+phone);
		
		//view page로 forward 이동해서 결과 응답하기
		return "friend/insert";
	}
	
	/*폼 전송을 추출하는 세가지 방법2*/
	/*
	 * "num" 이라는 파라미터 명으로 전달되는 파라미터 => @RequestParam int num 알아서 Integer 타입 변환*
	 * "name" 이라는 파라미터 명으로 전달되는 파라미터 => @RequestParam String name
	 * "phone" 이라는 파라미터 명으로 전달되는 파라미터 => @RequestParam String phone
	 */
	@RequestMapping("/friend/insert2")
	public String insert(@RequestParam int num, String name, String phone) {
		
		//추출된 정보 테스트로 출력해보기
		System.out.println(num+"|"+name+"|"+phone);
		
		return "friend/insert";
	}
	
	/*
	 * 컨트롤러의 메소드에 dto 를 인자로 받게 선언해 놓으면
	 * 요청 파라미터가 자동 추출 되어서 dto 에 저장되어서 전달된다.
	 * 
	 * "num" 이라는 파라미터 명으로 전달되는 파라미터 => dto 의 num 이라는 필드에 저장
	 * "name" 이라는 파라미터 명으로 전달되는 파라미터 => dto 의 name 이라는 필드에 저장
	 * "phone" 이라는 파라미터 명으로 전달되는 파라미터 => dto 의 phone 이라는 필드에 저장
	 * 
	 * syso 지워도 콘솔창에 출력됨.
	 */
	@RequestMapping("/friend/insert3")
	public String insert(@ModelAttribute FriendDto dto) {
		
		/*
		아래의 작업을 Spring 프레임 워크가 해준다.
		int num=Integer.parseInt(request.getParameter("num"));
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		FriendDto dto=new FriendDto();
		dto.setNum(num);
		dto.setName(name);
		dto.setPhone(phone);
		 */
		
		System.out.println(dto.getNum()+"|"+dto.getName()+"|"+dto.getPhone());
		return "friend/insert";
	}
}
