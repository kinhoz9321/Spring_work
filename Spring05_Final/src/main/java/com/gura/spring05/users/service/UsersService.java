package com.gura.spring05.users.service;

import com.gura.spring05.users.dto.UsersDto;

public interface UsersService {
	//회원 가입 처리를 하는 메소드
	public void addUser(UsersDto dto);
	//아이디가 존재하는지 여부를 리턴하는 메소드
	public boolean isExistId(String inputId);
}
