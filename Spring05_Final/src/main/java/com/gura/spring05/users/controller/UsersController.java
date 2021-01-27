package com.gura.spring05.users.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring05.users.dto.UsersDto;
import com.gura.spring05.users.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	//로그인 폼 요청 처리
	@RequestMapping("/users/loginform")
	public ModelAndView loginform(HttpServletRequest request, ModelAndView mView) {
		// GET 방식 파라미터 url 이라는 이름으로 전달되는 값이 있는지 읽어와보기
		String url=request.getParameter("url");
		//만일 넘어오는 값이 없다면 
		if(url==null){
			//로그인 후에 home.do 요청이 되도록 절대 경로를 구성한다.
			String cPath=request.getContextPath();
			url=cPath+"/home.do";
		}
		//쿠키에 저장된 아이디와 비밀번호를 담을 변수
		String savedId="";
		String savedPwd="";
		//쿠키에 저장된 값을 위의 변수에 저장하는 코드를 작성해 보세요.
		Cookie[] cooks=request.getCookies();
		if(cooks!=null){
			//반복문 돌면서 쿠키객체를 하나씩 참조해서 
			for(Cookie tmp: cooks){
				//저장된 키값을 읽어온다.
				String key=tmp.getName();
				//만일 키값이 savedId 라면 
				if(key.equals("savedId")){
					//쿠키 value 값을 savedId 라는 지역변수에 저장
					savedId=tmp.getValue();
				}
				if(key.equals("savedPwd")){
					savedPwd=tmp.getValue();
				}
			}
		}		
		//view page 에서 필요한 데이터를 담는다.
		mView.addObject("url", url);
		mView.addObject("savedId", savedId);
		mView.addObject("savedPwd", savedPwd);
		//view page 정보도 담는다.
		mView.setViewName("users/loginform");
		//리턴
		return mView;
	}
	
	@RequestMapping("/users/signup_form")
	public String signupForm() {
		return "users/signup_form";
	}
	
	//ajax 요청 처리
	@RequestMapping("/users/checkid")
	public ModelAndView checkid(@RequestParam String inputId, ModelAndView mView) {//@ReuquestParam 생략가능
		/*
		 * (@ReuquestParam String inputId)
		 * 는 
		 * String inputId=request.getParameter("inputId")
		 * 와 같다.
		 */
		//서비스를 이용해서 해당 아이디가 존재하는지 여부를 알아낸다.
		boolean isExist=service.isExistId(inputId);
		//ModelAndView 객체에 해당 정보를 담고 view page 로 forward 이동해서 응답
		//ModelAndView 에 담는 것은 request 에 담는 것과 같다.
		mView.addObject("isExist", isExist);
		mView.setViewName("users/checkid");
		return mView;
	}

	//회원 가입 요청처리
	/*
	 * form 전송은 보통 post 방식 요청인데 post 방식 요청만 받아들이도록 (주소창에 ?=으로 검색하는 GET 방식은 아예 차단)
	 * 컨트롤러에 설정하는게 일반적이다.
	 */
	@RequestMapping(value = "/users/signup", method = RequestMethod.POST)
	public ModelAndView insert(@ModelAttribute("dto") UsersDto dto, ModelAndView mView) {
		/*
		 * Dto 인 경우에 @ModelAttribute("key 값") 으로 설정하면
		 * 해당 Dto 가 request 영역에 설정한 "key 값" 으로 담긴다.
		 */
		service.addUser(dto);
		mView.setViewName("users/signup");
		return mView;
	}
	/*
	 * String id = request.getParameter("id");
      String pwd = request.getParameter("pwd");
      String email = request.getParameter("email");
      UsersDto dto = new UsersDto();
      dto.setId(id);
      dto.setPwd(pwd);
      dto.setEmail(email);
	 */
	
}
