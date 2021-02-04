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
 */