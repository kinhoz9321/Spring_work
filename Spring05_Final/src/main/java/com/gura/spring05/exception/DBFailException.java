package com.gura.spring05.exception;
/*
 * 일반적인 예외 클래스는 RuntimeException 클래스를 상속 받아서 만든다. (나중에 특별한 예외 클래스도 만들 것)
 * - 생성자의 인자로 예외 메세지를 전달받아서 부모 생성자에게 넘겨주면
 * '.getMessage() 메소드를 호출'해서 예외 메세지를 받아갈 수 있다.
 */
public class DBFailException extends RuntimeException{
	//생성자
	public DBFailException(String message) {
		super(message);//RuntimeException 클래스의 생성자에 message를 보내는 것
	}
}
