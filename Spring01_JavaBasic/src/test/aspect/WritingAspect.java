package test.aspect;

import org.aspectj.lang.annotation.After;
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
	@Before("execution(void write*())") //write로 시작하는 모든 메소드가 수행되게 이전에 이 작업을 해라 void를 빼도 되지만 리턴타입을 가리기 때문에 void 붙임.
	public void prepare() {
		System.out.println("pen을 준비해요");
	}
	@After("execution(void write*())") //write로 시작하는 모든 메소드가 수행되게 이전에 이 작업을 해라 void를 빼도 되지만 리턴타입을 가리기 때문에 void 붙임.
	public void prepare1() {
		System.out.println("pen을 다시 정리해요");
	}
}
