package com.gura.spring04.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.member.dao.MemberDao;
import com.gura.spring04.member.dto.MemberDto;

@Controller //component scan 을 통해서 bean이 된다.
public class MemberController {
	//MemberDao 인터페이스 type 을 주입(DI) 받아서 사용한다.
	@Autowired
	private MemberDao dao; //주입이 되려면 스프링이 관리하는 spring bean container 타입 중에 하나여야 함. (MemberDaoImpl 에서 @Repository 어노테이션을 붙이고, component scan 을 했기 때문에 bean 이 되었다.) @Autowired 해서 뭐든 주입할 수 있다.
	
	@RequestMapping("/member/list.do")
	public ModelAndView list(ModelAndView mView) {
		//1. 회원 목록을 얻어온다.
		List<MemberDto> list=dao.getList();
		
		//2. 회원 목록을 request scope 에 담고 view page 로 forward 이동해서 응답
		//2. Model(data) 를 담고
		mView.addObject("list", list);
		
		//3. view page 정보도 담고
		mView.setViewName("member/list");
		
		//4. 리턴해준다.
		return mView; //머쓱 조심하기;;
	}
}
