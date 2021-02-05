package test.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.util.WritingUtil;

public class MainClass3 {
	public static void main(String[] args) {
		//init.xml 문서를 로딩한다. (spring bean container 를 만든다.)
		ApplicationContext context=new ClassPathXmlApplicationContext("test/aop/init.xml");
		//spring bean container 에서 WritingUtil type 의 참조값 얻어오기
		WritingUtil util=context.getBean(WritingUtil.class);
		
		util.sendGreet("안녕하세요");
		util.sendGreet("좋은 아침 입니다.");
		//이런 단어가 들어가면 바보, 똥개 메소드 수행 안하게 하기. 메소드가 수행되기 이전에 스트링 조사 가능
		util.sendGreet("안녕 바보야!");
		util.sendGreet("뭘봐 똥개야");
		
		System.out.println("main 메소드가 종료 됩니다.");
	}
}
