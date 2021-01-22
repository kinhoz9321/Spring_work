<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/study.jsp</title>
</head>
<body>
<h1>공부 페이지 입니다.</h1>
<p>열심히 공부 하자!</p>
<a href="../home.do">인덱스(상대경로)</a><br />
<a href="${pageContext.request.contextPath }/home.do">인덱스 (절대경로)</a><br />
<a href="${pageContext.request.contextPath }/">인덱스(최상위경로)</a><br />
</body>
</html>