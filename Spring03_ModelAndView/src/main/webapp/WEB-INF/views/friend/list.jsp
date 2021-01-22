<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/friend/list.jsp</title>
<link rel="stylesheet" href="../resources/css/bootstrap.css" />
<!-- 
http://localhost:8888/spring03/friend/list.do 주소창 경로가 진짜 경로 상대경로를 짤 시 여기서 이동경로를 짜야한다.
http://localhost:8888/spring03/resources/css/bootstrap.css 
-->
</head>
<body>
<div class="container">
	<h1>친구 목록 입니다.</h1>
	<ul>
		<c:forEach var="tmp" items="${list }">
			<li>${tmp }</li>
		</c:forEach>
	</ul>
	<a href="../home.do">인덱스로 가기(상대경로)</a>
	<a href="${pageContext.request.contextPath }/">인덱스로 가기</a>
</div>
</body>
</html>