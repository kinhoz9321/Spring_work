<%@page import="test.cafe.dao.CafeDao"%>
<%@page import="test.cafe.dto.CafeDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. GET 방식 파라미터로 전달되는 자세히 보여줄 글 번호를 읽어온다.
	int num=Integer.parseInt(request.getParameter("num"));
	//2. 글번호를 이용해서 DB 에서 글정보를 읽어온다.
	CafeDto dto=CafeDao.getInstance().getData(num);
	//3. 글 조회수를 올린다.
	CafeDao.getInstance().addViewCount(num);
	//4. 응답한다.
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/detail.jsp</title>
<jsp:include page="../include/resource.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../include/navbar.jsp">
	<jsp:param value="cafe" name="thisPage"/>
</jsp:include>
<div class="container">
	<nav>
		<ul class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath }/">Home</a>
			</li>
			<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath }/cafe/list.jsp">글목록</a>
			</li>
			<li class="breadcrumb-item active">상세보기</li>
		</ul>
	</nav>	
	<table class="table table-bordered">
		<tr>
			<th>글번호</th>
			<td><%=dto.getNum() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=dto.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%=dto.getViewCount() %></td>
		</tr>
		<tr>
			<th>등록일</th>
			<td><%=dto.getRegdate() %></td>
		</tr>
		<tr>
			<td colspan="2">
				<div><%=dto.getContent() %></div>
			</td>
		</tr>
	</table>
	<%
		//session scope 에서 로그인된 아이디를 읽어와 본다. ( null 일수도 있음 )
		String id=(String)session.getAttribute("id");
	%>
	<ul>
		<li><a href="list.jsp">목록보기</a></li>
		<%if(dto.getWriter().equals(id)){ %>
			<li><a href="private/updateform.jsp?num=<%=dto.getNum()%>">수정</a></li>
			<li><a href="javascript:deleteConfirm()">삭제</a></li>
		<%} %>
	</ul>
</div>
<script>
	function deleteConfirm(){
		let isDelete=confirm("글을 삭제 하시겠습니까?");
		if(isDelete){
			location.href="private/delete.jsp?num=<%=dto.getNum()%>";
		}
	}
</script>
</body>
</html>








