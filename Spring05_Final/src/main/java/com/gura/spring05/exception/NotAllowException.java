package com.gura.spring05.exception;

public class NotAllowException extends RuntimeException{
	//생성자
	public NotAllowException(String msg) {
		super(msg);
	}
}
