package test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/*
 * Aspect : 공통 관심사 (횡단 관심사)
 * 
 * - 핵심 비즈니스 로직과는 상관 없는 공통 관심사를 따로 작성한다.
 */

@Aspect // aspect 역할을 할 수 있도록 필요한 어노테이션
@Component // component scan 을 통해서 bean 이 될 수 있도록
public class WritingAspect {
	//execution() 지시자
	//수행 이전
	@Before("execution(void write*())") //write로 시작하는 모든 메소드가 수행되게 이전에 이 작업을 해라 void를 빼도 되지만 리턴타입을 가리기 때문에 void 붙임.
	public void prepare() {
		System.out.println("pen을 준비해요");
	}
	//수행 이후
	@After("execution(void write*())") //write로 시작하는 모든 메소드가 수행되게 이전에 이 작업을 해라 void를 빼도 되지만 리턴타입을 가리기 때문에 void 붙임.
	public void prepare1() {
		System.out.println("pen을 다시 정리해요");
	}
	//수행 이전과 이후 Around 는 무조건 인자를 하나 전달받아야 한다.
	@Around("execution(void sendEmail(java.lang.String))")//sendEmail() 이건 인자로 아무것도 전달받지 않는 느낌. sendEmail(){} 이렇게
	public void emailConcern(ProceedingJoinPoint joinPoint) throws Throwable {
		//aspect 가 적용된 메소드가 수행되기 이전에 작업할 내용
		System.out.println("이전과 이후는 어떻게 구별하지?");
		System.out.println("joinpoint.proceed() 이전 작업");
		System.out.println("웹 브라우저를 실행해요");
		
		//aspect 가 적용된 메소드 호출해서 수행하기
		joinPoint.proceed(); // 이 메소드를 기준으로 핵심 비즈니스 로직 util.sendEmail("gura@naver.com"); 의 전 후에 작업이 나뉜다.
		
		//aspect 가 적용된 메소드가 리턴된 직후 작업할 내용
		System.out.println("joinpoint.proceed() 이후 작업 ...");
		System.out.println("웹브라우저를 닫아요.");
	}
	
	@Around("execution(void sendGreet(String))")
	public void greetConcert(ProceedingJoinPoint joinPoint) throws Throwable {
		/*
		 * 메소드에 전달되는 인자의 갯수는 1개
		 * 메소드에 전달되는 인자의 type 은 String
		 * joinPoint.getArgs() 는 Object[] type을 리턴한다
		 * Object[] 에 0번방에 전달된 인자가 Object type 으로 들어있다. 
		 * 
		 * 인자가 하나밖에 없으니까 0번방에 있다.
		 * 인자가 여러개면 0번방, 1번방, 2번방 이런식으로 들어감
		 */
		Object[] args=joinPoint.getArgs();
		//인자로 전달된 String type 의 참조값 얻어내기
		String greet=(String)args[0];
		boolean result=greet.contains("바보");
		boolean result1=greet.contains("똥개");
		if(result!=true && result1!=true) {
			System.out.println("greet : "+greet);
			joinPoint.proceed();
		}
	}
	
	@Around("execution(String getGreet())")
	public Object getConcern(ProceedingJoinPoint joinPoint) throws Throwable {
		//aspect 가 적용된 메소드가 리턴한 데이터의 참조값을 받아볼 수 있다.
		Object obj=joinPoint.proceed();
		//원래 type 으로 casting
		String returnedData=(String)obj;
		System.out.println("returnedData:"+returnedData);
		//aspect 에서 조건부로 다른 data를 리턴할 여지도 있다.
		String myData="맛있는 점심 되세요~";
		return myData;
	}
	
}
/*
 * 조건부로 proceed() 를 호출하지 않을수도 있다.
 */
