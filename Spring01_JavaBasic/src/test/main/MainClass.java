package test.main;

import test.mypac.GoodWeapon;
import test.mypac.Weapon;
import test.mypac.WeaponImpl;
/*
 * [ 객체 혹은 클래스 사이의 의존관계를 느슨하게 하는 방법 ] - 목적
 * 
 * - 방법 : 세가지 원칙을 지키면 느슨해진다.
 * 
 * 1. 인터페이스 type 을 적극 활용한다.
 * 2. 필요한 핵심 의존 객체를 직접 생성 (new) 하지 않는다. *** 그러면 공격을 어떻게 하지?
 * 3. 필요한 핵심 의존 객체를 다른 곳에서 받아서 사용한다. (init.xml Spring)
 * 
 * - 즉 필요한 핵심 의존 객체의 생성과 관리를 하는 무언가가 필요하다.
 * 	그걸 대신 해주는 게 스프링 프레임 워크이다.
 * 
 * - 세가지 원칙을 체계적으로 활용하기 위해서 스프링 프레임 워크를 사용한다.
 */
public class MainClass {
	public static void main(String[] args) {
		//무언가를 공격해야 한다. 어떻게 코딩하면 될까?
		
		/*1*/
		//공격하기 위해 필요한 객체(의존객체)를 직접 생성(new)해서 
		WeaponImpl a=new WeaponImpl();
		//해당객체의 메소드를 호출함으로써 목적을 달성했다.
		a.attack();
		
		/*2*/
		//Weapon 인터페이스 타입으로 받아보았다.
		Weapon a1=new WeaponImpl();
		//공격 성공!
		a1.attack();
		
		/*3*/
		//조금 더 좋은 공격무기를 사용하기 위해 WeaponImpl => GoodWeapon 으로 java code update 한다.
		Weapon a2=new GoodWeapon();
		a2.attack();
		
	}
}
/*
 * 어플리케이션은 계속해서 유지보수를 해야한다.
 * 
 * java code update는 큰일 (어려운 일)
 * java 프로그래밍을 하다보면 클래스들이 엮이는 일이 많다.
 * 하나를 업그레이드 하면 다른 곳들도 업그레이드 해줘야 할 가능성이 높음. (끝없는 업그레이드의 굴레)
 * 어떻게 하면 이런 비효율성을 줄일 수 있을까?
 * - 클래스들의 의존관계를 좀 느슨하게 한다. (무슨말일까?)
 * - 어떤 클래스를 수정했을 때 다른 클래스들에게 전파가 잘 안되게 한다. (spring frame work의 탄생 배경)
 * - 큰 어플리케이션의 유지보수를 편하게 해준다.
 */