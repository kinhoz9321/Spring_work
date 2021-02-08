package test.main4;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MainClass2 {
	public static void main(String[] args) {
		//회원 가입할 때 폼에 입력했던 비밀번호라고 가정
		String pwd="abcd1234";
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		//인코딩해서 DB 에 저장된 비밀번호라고 가정
		String savedPwd=encoder.encode(pwd);
		
		System.out.println("로그인 비밀번호 입력:");
		//로그인 할 때 입력한 비밀번호라고 가정
		String inputPwd=new Scanner(System.in).nextLine();
		
		//DB에 저장된 비밀번호와 입력한 비밀번호가 일치하는지 여부
		boolean isEqual=BCrypt.checkpw(inputPwd, savedPwd);
		if(isEqual) {
			System.out.println("비밀번호가 일치해요!");
		}else {
			System.out.println("비밀번호가 달라요!");
		}
	}
	
}
/*
실제 웹프로젝트에 적용하기
pwd -> encoding(암호화) -> DB 저장
login (id/pwd) -> DB(id 이용해서 select id,pwd and 암호화된 pwd 입력한 pwd 동일한지 비교 일치해야 로그인 처리 성공)
*/