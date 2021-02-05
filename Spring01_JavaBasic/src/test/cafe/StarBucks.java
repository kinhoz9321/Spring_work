package test.cafe;

import org.springframework.stereotype.Component;

@Component
public class StarBucks {
	public void orderOne(Americano a) {//메소드 안에 아메리카노 클래스 인자로 전달
		System.out.println("orderOne() / StarBucks 클래스");
	}
	
	public void orderTwo(Latte l, Americano a) {
		System.out.println("orderTwo() / StarBucks 클래스");
	}
	
	public void orderThree(Latte l, Milk m, Americano a) {
		System.out.println("orderThree() / StarBucks 클래스");
	}
}
