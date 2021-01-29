package com.gura.spring04.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dao.MemberDao;
import com.gura.spring04.member.dto.MemberDto;
import com.gura.spring04.member.service.MemberService;

/* component scan 을 통해서 bean 이 된다. (Spring Bean Container 에서 관리가 된다.) */
@Controller
public class MemberController {
	//핵심 의존객체 주입 받기
	@Autowired
	private MemberService service;
	
	@RequestMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		//MemberService 의 메소드를 호출하면서 ModelAndView 객체의 참조값을 전달해서 
		//"list" 라는 키값으로 회원목록이 담기도록 한다.
		service.getMemberList(mView);
		mView.setViewName("member/list");
		return mView;
	}
	//회원추가 폼 요청 처리
	@RequestMapping("/member/insertform")
	public String insertform() {
		//요청 처리할 비즈니스 로직은 없다.
		
		//view page 의 위치만 리턴해주면 된다.
		return "member/insertform";
	}
	//회원 추가 요청처리
	@RequestMapping("/member/insert")
	public ModelAndView insert(@ModelAttribute("dto") MemberDto dto,
			ModelAndView mView) {
		//MemberService 객체를 이용해서 회원정보를 추가한다.
		service.addMember(dto);
		//view page 정보도 담아서 
		mView.setViewName("member/insert");
		//리턴해 준다. 
		return mView;
	}
	//회원 정보를 삭제하는 메소드
	@RequestMapping("/member/delete")
	public ModelAndView delete(@RequestParam int num, 
				ModelAndView mView) {
		//회원 정보를 삭제하는 비즈니스 로직 수행
		service.deleteMember(num);
		//목록보기로 리다일렉트 이동 
		mView.setViewName("redirect:/member/list.do");
		return mView;
	}
	//회원정보 수정폼 요청 처리
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(@RequestParam int num,
			ModelAndView mView) {
		service.getMember(num, mView);
		//view page  로 forward 이동해서 응답
		mView.setViewName("member/updateform");
		return mView;
	}
	//회원정보 수정 요청 처리
	/*
	 *  Dto 에 @ModelAttribute("key 값") 을 선언하면
	 *  해당 객체가 "key 값" 으로 자동으로 request scope 에 담긴다.
	 */
	@RequestMapping("/member/update")
	public ModelAndView update(@ModelAttribute("dto") MemberDto dto,
			ModelAndView mView) {
		service.updateMember(dto);
		mView.setViewName("member/update");
		return mView;
	}
}