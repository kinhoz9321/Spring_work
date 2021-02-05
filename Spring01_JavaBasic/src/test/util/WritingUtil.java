package test.util;

import org.springframework.stereotype.Component;

@Component
public class WritingUtil {
	
	public void write1() {
		//System.out.println("pen 을 준비해요");
		System.out.println("편지를 써요");
	}
	
	public void write2() {
		//System.out.println("pen 을 준비해요");
		System.out.println("보고서를 써요");	
	}
	
	public void write3() {
		//System.out.println("pen 을 준비해요");
		System.out.println("일기를 써요");
	}
	//어디선가 이 메소드를 호출할 것. 메소드를 수행한 다음에 호출한 위치로 리턴됨.
	public void sendEmail(String address) {
		System.out.println(address+" 로 이메일을 보내요!");
	}
	
	public void sendGreet(String greet) {
		System.out.println(greet+" 라는 인사말을 보내요!");
	}
	
	public String getGreet() {
		return "맛점 하세요";
	}
	
}
/*
 * core logic 핵심로직
 * ~를 써요
 * 
 * pen 을 준비해요 = 핵심로직x
 * 준비과정
 * 횡단 관심사 cross concern (크로스 컨선)
 * 핵심 비즈니스 로직과는 상관없는 횡단 관심사
 * 
 * AOP 를 사용하면 횡단 관심사를 따로 작성하고 설정만으로 적용시킬 수 있다. ***
 * 
 * 이 클래스를 건드리지 않고 PEN 준비해요 를 들어가게 만든다. 어떻게?
 * 
 * 어스펙트 작업
 * 
 * 인자를 어떻게 얻어오느냐에 대한 수업? 어렵다! 이게 뭐람!
 */