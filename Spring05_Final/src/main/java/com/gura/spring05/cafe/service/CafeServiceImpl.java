package com.gura.spring05.cafe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gura.spring05.cafe.dao.CafeDao;
import com.gura.spring05.cafe.dto.CafeDto;
@Service
public class CafeServiceImpl implements CafeService{
	/*
	 * 하나의 서비스는 여러개의 DAO 에 의존할 수도 있다.
	 * 그 때 필드명이 같으면 안된다. 필드명을 구체적으로 사용하기.
	 */
	//의존객체 DI
	@Autowired
	private CafeDao cafeDao;
	
	/*
	응용프로그래밍 서버 실행했을 때 setter 메소드가 만들어진다.
	public void setCafeDao(CafeDao cafeDao) {
		this.cafeDao = cafeDao;
	}
	*/
	
	@Override
	public void saveContent(CafeDto dto) {
		cafeDao.insert(dto);
		
	}

}
