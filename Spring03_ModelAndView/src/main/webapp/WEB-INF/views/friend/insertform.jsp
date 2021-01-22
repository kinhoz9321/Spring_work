<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/friend/insertform.jsp</title>
</head>
<body>
<div class="container">
	<h1>친구 추가 폼 입니다.</h1>
	<form action="insert.do" method="post"><!-- /friend/insert.do -->
		<input type="text" name="num" placeholder="번호 입력"/>
		<input type="text" name="name" placeholder="이름 입력"/>
		<input type="text" name="phone" placeholder="전화 번호 입력"/>
		<button type="submit">추가</button>
	</form>
	<h1>친구 추가 폼 입니다.2</h1>
	<form action="insert2.do" method="post"><!-- /friend/insert.do -->
		<input type="text" name="num" placeholder="번호 입력"/>
		<input type="text" name="name" placeholder="이름 입력"/>
		<input type="text" name="phone" placeholder="전화 번호 입력"/>
		<button type="submit">추가2</button>
	</form>
	<h1>친구 추가 폼 입니다.3</h1>
	<form action="insert3.do" method="post"><!-- /friend/insert.do -->
		<input type="text" name="num" placeholder="번호 입력"/>
		<input type="text" name="name" placeholder="이름 입력"/>
		<input type="text" name="phone" placeholder="전화 번호 입력"/>
		<button type="submit">추가3</button>
	</form>
</div>
</body>
</html>