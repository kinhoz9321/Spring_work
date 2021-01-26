package com.gura.spring04.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dao.MemberDao;
import com.gura.spring04.member.dto.MemberDto;

//비즈니스 로직을 처리하는 서비스는 @Service 어노테이션을 붙여서 만든다. 
@Service
public class MemberServiceImpl implements MemberService{
	//핵심 의존객체 DI
	@Autowired
	private MemberDao dao;
	
	@Override
	public void addMember(MemberDto dto) {
		dao.insert(dto);
		
	}

	@Override
	public void updateMember(MemberDto dto) {
		dao.update(dto);
		
	}

	@Override
	public void deleteMember(int num) {
		dao.delete(num);
		
	}

	//번호를 이용해서 회원정보를 얻어와서 ModelAndView 객체에 담아주는 메소드
	@Override
	public void getMember(int num, ModelAndView mView) {
		MemberDto dto=dao.getData(num);
		mView.addObject("dto", dto);
	}

	//회원 목록을 얻어와서 ModelAndView 객체에 담아주는 메소드
	@Override
	public void getMemberList(ModelAndView mView) {
		List<MemberDto> list=dao.getList();
		mView.addObject("list", list);
	}

}
