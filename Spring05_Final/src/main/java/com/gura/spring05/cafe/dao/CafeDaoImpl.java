package com.gura.spring05.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring05.cafe.dto.CafeDto;

@Repository
public class CafeDaoImpl implements CafeDao{
	//핵심의존객체 DI
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(CafeDto dto) {
		session.insert("cafe.insert", dto);
	}

	@Override
	public void update(CafeDto dto) {
		session.update("cafe.update", dto);	
	}

	@Override
	public void delete(int num) {
		session.delete("cafe.delete", num);
	}

	@Override
	public CafeDto getData(int num) {
		CafeDto dto=session.selectOne("cafe.getData", num);
		return dto;
	}

	@Override
	public List<CafeDto> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CafeDto> getList(CafeDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(CafeDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
