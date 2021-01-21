package com.gura.spring01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//1. 컨트롤러 역할을 할 수 있도록 @Controller 어노테이션을 붙인다. (상속의 역할)
@Controller
public class FortuneController {
	
	//2. 이 메소드로 어떤 요청을 처리 할 것인지 @RequestMapping 어노테이션에 명시한다.
	@RequestMapping("/fortune.do")
	public String fortune(HttpServletRequest request) { //리턴 type 은 String, 메소드명은 마음대로 지을 수 있다.
		//service에서 하던 일을 여기서 한다고 보면 됨.ㄴ
		/*
		 * 4. 메소드 안에서 필요한 객체(HttpServletRequest, HttpSession ...) 가 있으면
		 * 메소드의 인자로 선언만 하면 알아서 해당 객체의 참조값이 전달된다.
		 *  
		 * fortune(HttpServletRequest request, Httpsession session) 필요한 걸 선언하면 자동으로 전달된다.
		 */
		
		// jsp 페이지에 전달할 데이터(Model)
		String fortuneToday="동쪽으로 가면 귀인을 만나요";
		// request scope 에 담는다. key in jsp page ${fortuneToday}
		request.setAttribute("fortuneToday", fortuneToday);
		
		/*
		 * 3. forward 이동할 jsp 페이지의 위치를 문자열로 리턴해준다.
		 * 
		 * 리턴된 문자열의 접두어(prefix) 는 "WEB-INF/views/" 가 되고
		 * 리턴된 문자열의 접미어(suffix) 는 ".jsp" 가 된다.
		 * spring > appServlet > servlet-context.xml 에서 접두어 접미어에 대한 정보를 확인할 수 있다.
		 * 
		 * 따라서 "fortune" 을 리턴하면
		 * "/WEB-INF/views/" + "fortune" + ".jsp" 문자열이 만들어져서
		 * 결과는
		 * "/WEB-INF/views/fortune.jsp" 가 jsp 페이지의 위치가 된다.
		 * 
		 * 그러므로 해당 위치를 찾아서 jsp 파일을 만들어서 응답하면 된다.
		 */
		
		return "fortune"; //내부적으로 알아서 forward 해줌. default 가 forward.
	}
}
