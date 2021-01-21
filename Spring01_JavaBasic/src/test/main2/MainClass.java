package test.main2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.mypac.Weapon;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("test/main2/init.xml");
		/*
		 * 스프링이 객체를 직접 생성해서 관리하는 공간을 Spring Bean Container 라고 한다.
		 * 
		 * 해당 컨테이너에서 참조값을 가지고 올 때 type 으로 찾아서 가지고 올 수도 있다.
		 * 
		 * 그렇게 하기 위해서는 .getBean() 메소드를 호출하면서 Class type 을 인자로 전달하면 된다.
		 * 
		 * Class type은 타입.class 와 같은 형태로 참조할 수 있다.
		 * 
		 * 단, 컨테이너에 해당 type 객체가 오직 1개만 있는 경우에 가능하다.
		 * 같은 type 이 여러개 있다면 id를 지정해서 이름으로 찾아와야 한다.
		 */
		Weapon w=context.getBean(Weapon.class); //casting 이 필요 없음
		w.attack();
	}
}
