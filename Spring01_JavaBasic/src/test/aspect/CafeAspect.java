package test.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import test.cafe.Americano;
import test.cafe.Latte;
import test.cafe.Milk;

@Aspect
@Component //bean 이 되어야 하기 때문에
public class CafeAspect {
	// order*(..)의 의미는 order로 시작하는 모든 메소드를 의미한다.
	@Around("execution(void order*(..))")//메소드에 전달되는 인자가 동적이다. 있을수도 있고, 없을수도 있다.
	public void starbucksConcern(ProceedingJoinPoint joinPoint) throws Throwable {
		//인자로 전달된 객체의 참조값을 Object[] 로 얻어내기 어떤 타입이든 다 담을 수 있으므로 object 사용
		Object[] args=joinPoint.getArgs(); //전달이 안되면 방의 갯수 0개, 1개면 1개, ...
		//반복문 돌면서 참조값을 하나씩 불러내서
		for(Object tmp:args) {
			//type 을 확인한다. 원하는 타입을 찾는 방법. 반복문을 돌아야 한다. 타입을 일일이 확인하기 위해서.
			if(tmp instanceof Americano) {// instanceof 연산자를 이용해서 type 확인 Americano type 이면 true가 나옴.
				//Americano type 이 맞으면 안전하게 casting 할 수 있다.
				Americano a=(Americano)tmp;
				a.drinkAmericano();
			}else if(tmp instanceof Latte) {//타입을 확인 하는 작업 필요
				Latte l=(Latte)tmp;
				l.drinkLatte();
			}else if(tmp instanceof Milk) {
				Milk m=(Milk)tmp;
				m.drinkMilk();
			}
		}
		System.out.println("starbucksConcern() 입니다. / CafeAspect 클래스");
		joinPoint.proceed();
	}
}
