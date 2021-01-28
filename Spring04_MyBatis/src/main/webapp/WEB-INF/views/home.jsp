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
		<li><a href="json01.do">json 테스트1</a></li>
		<li><a href="json02.do">json 테스트2</a></li>
		<li><a href="json03.do">json 테스트3</a></li>
		<li><a href="json04.do">json 테스트4</a></li>
	</ul>
	<h2>파일 업로드 테스트</h2>
	<form action="upload.do" method="post" enctype="multipart/form-data">
		제목 <input type="text" name="title"/><br />
		첨부파일 <input type="file" name="myFile"/><br />
		<button type="submit">업로드</button>
	</form>
	<h2>파일 업로드 테스트2 (dto 사용)</h2>
	<form action="upload2.do" method="post" enctype="multipart/form-data">
		제목 <input type="text" name="title"/><br />
		첨부파일 <input type="file" name="myFile"/><br />
		<button type="submit">업로드</button>
	</form>
</div>
</body>
</html>