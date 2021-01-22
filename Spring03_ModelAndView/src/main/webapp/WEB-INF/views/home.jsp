<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
</head>
<body>
<div class="container">
	<h1>인덱스 페이지 입니다.</h1>
	<ul>
		<li><a href="test/play.html">놀러가기(DispatcherServlet 을 거치지 않음)</a></li>
		<li><a href="test/study.jsp">공부하기(DispatcherServlet 을 거치지 않음)</a></li>
		<li><a href="friend/list.do">친구 목록 보기</a></li>
		<li><a href="friend/list2.do">친구 목록 보기2</a></li>
	</ul>
	<h2>공지 사항 입니다.</h2>
	<ul>
		<c:forEach var="tmp" items="${noticelist }">
			<li>${tmp }</li>
		</c:forEach>
	</ul>
</div>
</body>
</html>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
jstl 이 spring 에 내장되어 기본적으로 제공함.
jsp 페이지 만들었을 때 자동으로 추가되도록 설정.
window - preference - templates - jsp files - editors - templates - New JSP File (html 5) - 추가 설정 붙여넣기
--%>