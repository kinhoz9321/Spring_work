<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/error/not_allow.jsp</title>
</head>
<body>
<div class="container">
	<h1>허용되지 않는 요청이얌</h1>
	<p>${exception.message }</p>
	<a href="${pageContext.request.contextPath }/">인덱스로 돌아가기</a>
</div>
</body>
</html>
<!-- 
에러 페이지 확인을 위해 주소창 검색 방법
http://localhost:8888/spring05/file/private/delete.do?num=280
 -->