<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//session scope 에 "id" 라는 키값으로 저장된 문자열이 있는지 읽어와 본다.
	String id=(String)session.getAttribute("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/home.jsp</title>
<jsp:include page="include/resource.jsp"></jsp:include>
</head>
<body>
<jsp:include page="include/navbar.jsp"></jsp:include>
<div class="container">
	<%-- 만일 id 가 null 이 아니면 로그인을 한 상태이다. --%>
	<%if(id != null){ %>
		<p> 
			<a href="users/private/info.jsp"><%=id %></a> 님 로그인중...
			<a href="users/logout.jsp">로그아웃</a>
		</p>
	<%} %>
	<h1>인덱스 페이지 입니다.</h1>
	<ul>
		<li><a href="users/signup_form.do">회원가입</a></li>
		<li><a href="users/loginform.do">로그인</a></li>
		<li><a href="cafe/list.do">카페 글 목록 보기</a></li>
		<li><a href="file/list.do">자료실 목록 보기</a></li>
		<li><a href="gallery/list.do">겔러리 목록 보기</a></li>
		<li><a href="file/list2.do">자료실 목록보기(MVC 페턴)</a></li>
		<li><a href="test/error_test.do">500 오류나는 페이지</a></li>
	</ul>
</div>
</body>
</html>



