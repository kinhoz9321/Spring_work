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

@Controller
public class MemberController {
	// MemberDao 인터페이스 type 을 주입(DI) 받아서 사용한다.
	@Autowired
	private MemberDao dao;
	
	@RequestMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		//1. 회원 목록을 얻어온다.
		List<MemberDto> list=dao.getList();
		//2. 회원 목록을 request scope 에 담고 view page 로 forward 이동해서 응답
		mView.addObject("list", list);
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
	public ModelAndView insert(@ModelAttribute MemberDto dto,
			ModelAndView mView) {
		//DB 에 저장하는 비즈니스 로직 수행
		dao.insert(dto);
		//ModelAndView  객체에 dto 라는 키값으로 dto 를 담고
		mView.addObject("dto", dto);
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
		dao.delete(num);
		//목록보기로 리다일렉트 이동 
		mView.setViewName("redirect:/member/list.do");
		return mView;
	}
	//회원정보 수정폼 요청 처리
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(@RequestParam int num,
			ModelAndView mView) {
		//수정할 회원의 정보를 얻어오는 비즈니스 로직 수행
		MemberDto dto=dao.getData(num);
		//ModelAndView 객체에 수정할 회원의 정보를 담고
		mView.addObject("dto", dto);
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
		//회원정보를 수정반영하는 비즈니스 로직 수행
		dao.update(dto);
		mView.setViewName("member/update");
		return mView;
	}
}