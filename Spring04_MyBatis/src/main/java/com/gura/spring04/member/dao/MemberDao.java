package com.gura.spring04.member.dao;

import java.util.List;

import com.gura.spring04.member.dto.MemberDto;

public interface MemberDao {
	//회원 목록을 리턴하는 메소드
	public List<MemberDto> getList();
}
