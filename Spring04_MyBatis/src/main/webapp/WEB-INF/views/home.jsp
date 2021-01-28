<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
</head>
<body>
<div class="container">
	<h1>인덱스 페이지 입니다.</h1>
	<ul>
		<li><a href="member/list.do">회원 목록보기</a></li>
	</ul>
	<h2>파일 업로드 테스트</h2>
	<form action="upload.do" method="post" enctype="multipart/form-data">
		제목 <input type="text" name="title"/><br />
		첨부파일 <input type="file" name="myFile"/><br />
		<button type="submit">업로드</button>
	</form>
</div>
</body>
</html>