<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/list.jsp</title>
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
			<li class="breadcrumb-item active">글목록</li>
		</ul>
	</nav>
	<a href="private/insertform.jsp">새글 작성</a>
	<h1>카페 글 목록 입니다.</h1>
	<table class="table table-striped">
		<thead class="thead-dark">
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>조회수</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="tmp" items="list">
			<tr>
				<td>${tmp.num}</td>
				<td>${tmp.writer}</td>
				<td><a href="detail.jsp?num=${tmp.num}">${tmp.title}</a></td>
				<td>${tmp.viewCount}</td>
				<td>${tmp.regdate}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<nav>
		<ul class="pagination justify-content-center">
			<%if(startPageNum != 1){ %>
				<li class="page-item">
					<a class="page-link" href="list.jsp?pageNum=<%=startPageNum-1 %>&condition=<%=condition%>&keyword=<%=encodedK%>">Prev</a>
				</li>
			<%}else{ %>
				<li class="page-item disabled">
					<a class="page-link" href="javascript:">Prev</a>
				</li>
			<%} %>
			<%for(int i=startPageNum; i<=endPageNum; i++) {%>
				<%if(i==pageNum){ %>
					<li class="page-item active">
						<a class="page-link" href="list.jsp?pageNum=<%=i %>&condition=<%=condition%>&keyword=<%=encodedK%>"><%=i %></a>
					</li>
				<%}else{ %>
					<li class="page-item">
						<a class="page-link" href="list.jsp?pageNum=<%=i %>&condition=<%=condition%>&keyword=<%=encodedK%>"><%=i %></a>
					</li>
				<%} %>
			<%} %>
			<%if(endPageNum < totalPageCount){ %>
				<li class="page-item">
					<a class="page-link" href="list.jsp?pageNum=<%=endPageNum+1 %>&condition=<%=condition%>&keyword=<%=encodedK%>">Next</a>
				</li>
			<%}else{ %>
				<li class="page-item disabled">
					<a class="page-link" href="javascript:">Next</a>
				</li>
			<%} %>
		</ul>
	</nav>
	<form action="list.jsp" method="get">
		<label for="condition">검색조건</label>
		<select name="condition" id="condition">
			<option value="title_content" <%=condition.equals("title_content") ? "selected" : "" %>>제목+내용</option>
			<option value="title" <%=condition.equals("title") ? "selected" : "" %>>제목</option>
			<option value="writer" <%=condition.equals("writer") ? "selected" : "" %>>작성자</option>
		</select>
		<input type="text" name="keyword" placeholder="검색어..." value="<%=keyword%>"/>
		<button type="submit">검색</button>
	</form>
	<%-- 만일 검색 키워드가 존재한다면 몇개의 글이 검색 되었는지 알려준다. --%>
	<%if(!keyword.equals("")){ %>
		<div class="alert alert-success">
			<strong><%=totalRow %></strong> 개의 자료가 검색되었습니다.
		</div>
	<%} %>
</div>
</body>
</html>







