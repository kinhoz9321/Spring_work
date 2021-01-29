package com.gura.spring05.cafe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.cafe.dto.CafeDto;

public interface CafeService {
	//새글을 저장하는 메소드 (리턴타입, 메소드명, 인자)
	public void saveContent(CafeDto dto);
	//글목록을 얻어오고 페이징 처리에 필요한 값들을 ModelAndView 객체에 담아주는 메소드
	public void getList(ModelAndView mView, HttpServletRequest request);
}
