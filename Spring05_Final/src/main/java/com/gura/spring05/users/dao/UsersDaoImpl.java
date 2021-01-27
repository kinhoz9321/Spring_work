package com.gura.spring05.users.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring05.users.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao{
	
	//핵심 의존 객체(DI)
	@Autowired
	private SqlSession session;

	@Override
	public void updateProfile(UsersDto dto) {
		
		/*
		 * mapper namespace => users
		 * sql id => updateProfile
		 * parameterType => usersdto
		 */
		
		session.update("users.updateProfile", dto);
		
	}

	@Override
	public boolean isExist(String id) {
		
		/*
		 * mapper namespace => users
		 * sql id => isExist
		 * parameterType => String
		 * resultType => UsersDto
		 */
		//id 가 존재하면 (이미 등록된 아이디) null 이 아니고 존재하지 않으면 null 이다.
		UsersDto dto=session.selectOne("users.isExist", id);
		//dto.getId() - 값이 null일때 이렇게 메소드를 호출하려고 하면 널포인트 익셉션 발생. 값이 null인 건 상관없다. 
		if(dto==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public boolean updatePwd(UsersDto dto) {
		int count=session.update("users.updatePwd", dto);
		if(count==0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void update(UsersDto dto) {
		session.update("users.update", dto);
		
	}

	@Override
	public void delete(String id) {
		session.delete("users.delete", id);
		
	}

	@Override
	public UsersDto getData(String id) {
		
		/*
		 * mapper namespace => users
		 * sql id => getData
		 * parameterType => String
		 * resultType => UsersDto
		 */
		
		UsersDto dto=session.selectOne("users.getData", id);
		return dto;
	}

	@Override
	public boolean isValid(UsersDto dto) {
		
		/*
		 * mapper namespace => users
		 * sql id => isValid
		 * parameterType => UsersDto
		 * resultType => String
		 */
		
		String id=session.selectOne("users.isValid", dto);
		if(id==null) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void insert(UsersDto dto) {
		session.insert("users.insert", dto);
		
	}

}
