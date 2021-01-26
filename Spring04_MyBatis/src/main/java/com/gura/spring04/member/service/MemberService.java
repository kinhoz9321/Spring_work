package com.gura.spring04.member.service;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dto.MemberDto;

/*
 * 비즈니스 로직을 처리할 서비스가 구현할 인터페이스 정의하기
 */
public interface MemberService {
	//회원 한명의 정보를 추가하는 메소드
	public void addMember(MemberDto dto);
	//회원 한명의 정보를 수정하는 메소드
	public void updateMember(MemberDto dto);
	//회원 한명의 정보를 삭제하는 메소드
	public void deleteMember(int num);
	//회원 한명의 정보를 ModelAndView 에 담아주는 메소드
	public void getMember(int num, ModelAndView mView);
	//회원 목록을 ModelAndView 에 담아주는 메소드
	public void getMemberList(ModelAndView mView);
}
