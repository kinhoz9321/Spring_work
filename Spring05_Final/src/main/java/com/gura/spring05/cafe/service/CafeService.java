package com.gura.spring05.cafe.service;

import com.gura.spring05.cafe.dto.CafeDto;

public interface CafeService {
	//새글을 저장하는 메소드 (리턴타입, 메소드명, 인자)
	public void saveContent(CafeDto dto);
}
