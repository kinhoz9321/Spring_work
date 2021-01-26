package com.gura.spring05.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gura.spring05.users.dao.UsersDao;
import com.gura.spring05.users.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersDao dao;
	
	@Override
	public void addUser(UsersDto dto) {
		dao.insert(dto);
	}

	@Override
	public boolean isExistId(String inputId) {
		//id 존재 여부를 리턴해준다.
		return dao.isExist(inputId);
	}

}
