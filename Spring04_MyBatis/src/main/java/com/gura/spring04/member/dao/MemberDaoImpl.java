package com.gura.spring04.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring04.exception.DBFailException;
import com.gura.spring04.member.dto.MemberDto;

//component scan 을 통해서 bean 이 되도록 어노테이션을 붙여준다.
//Dao 에는 @Repository 라는 어노테이션을 붙여야 한다.
@Repository
public class MemberDaoImpl implements MemberDao{ //구현 클래스
	/*sqlsession type DI SqlSessionTemplate servlet-context.xml*/
	
	/*
	 * servlet-context.xml 문서에 bean 설정으로 bean 이 된
	 * SqlsessionTemplate 객체의 참조값을 필드에 주입 (DI) 받는 방법은
	 * @AutoWired 어노테이션을 필드 선언 앞이나 위에 붙여주면 된다.
	 * 단, MemberDaoImpl 객체도 bean 이 되어야 한다. 
	 * 
	 * 작성법 익히기!
	 */
	@Autowired
	private SqlSession session; //SqlsessionTemplate 객체의 참조값 DI (즉, null 이 아님)
	
	@Override
	public List<MemberDto> getList() {
		/*
		 * 3가지 정보 확인
		 * Mapper.xml 문서의 namespace => member
		 * sql 의 id => getList
		 * parameterType => MemberDto
		 */
		//.selectList() 를 호출했을 때 resultType 이 곧 List 의 Generic type 이 됩니다.
		List<MemberDto> list=session.selectList("member.getList");//(namespace.id)
		
		return list;
	}

	@Override
	public void insert(MemberDto dto) {
		/*
		 * 3가지 정보 확인
		 * Mapper.xml 문서의 namespace => member
		 * sql 의 id => insert
		 * parameterType => MemberDto
		 */
		session.insert("member.insert", dto);
		
	}

	@Override
	public void update(MemberDto dto) {
		/*
		 * 3가지 정보 확인
		 * Mapper.xml 문서의 namespace => member
		 * sql 의 id => update
		 * parameterType => MemberDto
		 */
		session.update("member.update", dto);
		
	}

	@Override
	public void delete(int num) {
		/*
		 * 3가지 정보 확인
		 * Mapper.xml 문서의 namespace => member
		 * sql 의 id => delete
		 * parameterType => int
		 */
		int count=session.delete("member.delete", num);
		if(count==0) {
			throw new DBFailException("삭제 실패 되었습니다.(삭제할 회원정보가 없습니다.)");
		}
		/*
		 * 예외를 발생시키는 방법
		 * throw (예약어)
		 * 예외 객체 생성해서 던지기.
		 */
	}

	@Override
	public MemberDto getData(int num) {
		/*
		 * 3가지 정보 확인
		 * Mapper.xml 문서의 namespace => member
		 * sql 의 id => getData
		 * parameterType => int
		 * resultType => MemberDto
		 */
		//.selectOne() 호출했을 때 resultType 이 곧 리턴 타입이 됩니다.
		MemberDto dto=session.selectOne("member.getData", num);
		
		return dto;
	}

}
