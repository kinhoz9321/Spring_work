<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/fortune.jsp</title>
</head>
<body>
<h1>오늘의 운세 페이지</h1>
<p>오늘의 운세: ${requestScope.fortuneToday } </p>
</body>
</html>
<!-- 
fortune.do 요청에 대해서 fortune.jsp 페이지가 forward 이동됨을 알 수 있다.
requestScope. 생략 가능
 -->