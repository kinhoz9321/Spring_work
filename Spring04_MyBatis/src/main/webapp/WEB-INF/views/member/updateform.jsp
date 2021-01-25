<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/updateform.jsp</title>
</head>
<body>
	<h1>회원 정보 수정 폼 입니다.</h1>
	<form action="update.do" method="post">
		<!-- 회원정보를 수정 반영할때 번호도 필요하기 때문에 폼 제출될때 같이 제출되도록 한다. -->
		<input type="hidden" name="num" value="${dto.num }"/>
		<!--  아래 번호는 단순 display 용도이다.  -->
		번호 <input type="text" value="${dto.num }" disabled/> <br/>
		이름 <input type="text" name="name" value="${dto.name }"/><br/>
		주소 <input type="text" name="addr" value="${dto.addr }"/><br/>
		<button type="submit">수정확인</button>
		<button type="reset">취소</button>
	</form>
</body>
</html>