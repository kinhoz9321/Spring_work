package com.gura.spring05.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gura.spring05.exception.NotAllowException;
import com.gura.spring05.file.dao.FileDao;
import com.gura.spring05.file.dto.FileDto;

@Aspect
@Component
public class FileAspect {
	// dao 의존객체 만들기
	@Autowired
	private FileDao dao;
	/*
	 * com.gura.spring05.cafe.service 패키지에 있는 
	 * 모든 클래스 중에 delete 로 시작하는 모든 메소드에 적용할 aspect
	 * execution(풀패키지 적어주기 *.delete*(..)-*모든메소드에 delete* delete 로 시작하는 모든 메소드 라는 뜻)
	 * (..) 인자가 있든 없든, 하나든 두개든, 타입도 상관없다는 의미
	 * 만일 점점이 없으면 메소드의 인자가 아무것도 없어야 한다.
	 * int 면 인자면 하나 타입은 인트타입
	 * int, String 인자는 두개 타입은 int, string타입 전달받는 것
	 * * 인자는 하나 어떤 타입이든 상관없다.
	 * *,* 인자는 두개 어떤 타입이든 상관없다.
	 */
	@Around("execution(void com.gura.spring05.file.service.*.delete*(..))")//delete 앞 이라고 명시 *
	public void checkDelete(ProceedingJoinPoint joinPoint) throws Throwable {//우리가 직접 호출하는게 아니기 때문에  throws 해도 상관없다. 예외 떠넘기기.
		//필요한 값을 담을 지역 변수 미리 만들기 (메소드에 전달된 인자로부터 찾아야 한다.)
		int num=0;
		HttpServletRequest request=null;
		//메소드의 인자로 전달된 값을 배열로 얻어내기
		Object[] args=joinPoint.getArgs();
		//반복문 돌면서
		for(Object tmp:args) {//file 번호와 HttpServletRequest 가 존재해야 한다.
			//필요한 type 을 찾아서 casting 한다.
			if(tmp instanceof Integer) {//instanceof int 는 없다. 정수를 찾을 때는 Integer 로 / instanceof 타입을 찾을 때 사용
				//자료실 파일 번호
				num=(Integer)tmp;
			}else if(tmp instanceof HttpServletRequest) {//request를 이용해서 session 객체의 참조값을 얻어내기 위해 사용
				//HttpSession 을 얻어낼 HttpServletRequest 객체 / request만 있어도 session 의 참조값을 얻어낼 수 있기 때문
				request=(HttpServletRequest)tmp;
			}
		}
		/*
		 * if문이랑 else if문 둘 중에 하나만 실행됨.
		 * for문을 돌면서 if문의 num과 else if문의 request를 다 담는 것.
		 */
		//파일 번호를 이용해서 파일 정보를 얻어온다.
		FileDto dto=dao.getData(num);
		//로그인된 아이디
		String id=(String)request.getSession().getAttribute("id"); //else if 에서 얻어낸 참조값으로 id 참조값 얻어내기
		//만일 로그인된 아이디와 파일 작성자가 다르다면
		if(!id.equals(dto.getWriter())) {//1. 조건부로 익셉션 발생시키기 -> ExceptionController notAllow 이동
			throw new NotAllowException("남의 파일을 지울 수 없습니다");//exceptioncontroller 로 간다.
		}//익셉션 발생안하면 정상 수행됨. fileservice와 비교해볼 것. 
		joinPoint.proceed();// proceed는 그냥 호출해주는 것. 익셉션은 throw 해준다.
	}
	/*
	 * throw => 예외발생시키기
	 * throws => 예외떠넘김
	 */
}
