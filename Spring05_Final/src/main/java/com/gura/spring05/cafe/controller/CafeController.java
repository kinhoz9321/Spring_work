package com.gura.spring05.cafe.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	//댓글 삭제 기능
	@RequestMapping("/cafe/private/comment_delete")
	public ModelAndView commentDelete(HttpServletRequest request,
			ModelAndView mView, @RequestParam int ref_group) {
		service.deleteComment(request);
		mView.setViewName("redirect:/cafe/detail.do?num="+ref_group);
		return mView;
	}
	
	//새 댓글 저장 요청 처리
	@RequestMapping(value = "/cafe/private/comment_insert", method = RequestMethod.POST)
	public String commentInsert(HttpServletRequest request, @RequestParam int ref_group) {
		//새 댓글 저장하고
		service.saveComment(request);
		//글 자세히 보기로 다시 리다일렉트 이동 시킨다.
		//ref_group 은 자세히 보기 했던 글번호
		return "redirect:/cafe/detail.do?num="+ref_group;
	}
	
	//글 삭제 요청 처리
	@RequestMapping("/cafe/private/delete")
	public String delete(@RequestParam int num) {
		service.deleteContent(num);
		return "cafe/private/delete";
	}
	
	//글 수정 폼 요청 처리
	@RequestMapping("/cafe/private/updateform")
	public ModelAndView updateform(@RequestParam int num, ModelAndView mView) {
		service.getDetail(num, mView);
		mView.setViewName("cafe/private/updateform");
		return mView;
	}
	
	//글 수정 요청 처리
	@RequestMapping("/cafe/private/update")
	public String update(@ModelAttribute("dto") CafeDto dto) {
		service.updateContent(dto);
		return "cafe/private/update";
	}
	
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
