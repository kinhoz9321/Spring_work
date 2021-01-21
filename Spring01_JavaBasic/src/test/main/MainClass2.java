package test.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.mypac.Weapon;

public class MainClass2 {
	public static void main(String[] args) {
		//init.xml 문서를 로딩해서 Spring 이 생성할 객체는 생성해서 관리하게 하기
		ApplicationContext context=new ClassPathXmlApplicationContext("test/main/init.xml");
		
		/*
		 *  (Spring 이 관리하는 객체 중에 id 가 myWeapon 인 객체의 참조값을 
		 *  Object type 으로 리턴받아서) = context.getBean()에 대한 설명
		 *  Weapon 인터페이스 type 으로 casting 해서
		 *  w 라는 이름의 지역 변수에 참조값을 담기
		 */
		Weapon w=(Weapon)context.getBean("myWeapon");
		//context.getBean() init.xml bean 호출 이라고 생각.
		//메소드 호출해서 원하는 목적 달성하기
		w.attack();
	}
}
/*
 * mainclass2 는 weaponimpl에 의존하고 있지 않다. import 되지 않음.
 * init.xml 을 수정하면 공격의 성능이 달라진다.
 * 
 * 우리가 어제까지 만들던 웹사이트를 스프링을 사용하면 의존관계를 느슨하게 해서 유지보수가 편하게 개발할 수 있다.
 */