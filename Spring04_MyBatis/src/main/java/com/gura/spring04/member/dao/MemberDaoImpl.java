package com.gura.spring04.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring04.member.dto.MemberDto;

//component scan 을 통해서 bean 이 되도록 어노테이션을 붙여준다.
//Dao 에는 @Repository 라는 어노테이션을 붙여야 한다.
@Repository
public class MemberDaoImpl implements MemberDao{
	/*sqlsession type DI SqlSessionTemplate servlet-context.xml*/
	
	/*
	 * servlet-context.xml 문서에 bean 설정으로 bean 이 된
	 * SqlsessionTemplate 객체의 참조값을 필드에 주입 (DI) 받는 방법은
	 * @AutoWired 어노테이션을 필드 선언 앞이나 위에 붙여주면 된다.
	 * 단, MemberDaoImpl 객체도 bean 이 되어야 한다. 
	 */
	@Autowired
	private SqlSession session; //SqlsessionTemplate 객체의 참조값 DI (즉, null 이 아님)
	
	@Override
	public List<MemberDto> getList() {
		//이렇게만 쓰면 완성
		List<MemberDto> list=session.selectList("member.getList");//(namespace.id)
		
		return list;
	}

}
