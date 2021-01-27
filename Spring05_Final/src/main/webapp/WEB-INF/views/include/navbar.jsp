<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 <nav class="navbar navbar-dark navbar-expand-sm fixed-top">
 	<div class="container">
 		<a class="navbar-brand" href="${pageContext.request.contextPath}/">
 			<img style="width:30px","heighte="30px" src="${pageContext.request.contextPath}/resources/images/rabbit_1.png"/>
 		</a>
		<a class="navbar-brand" href="${pageContext.request.contextPath}/">NewSite</a>
		<button class="navbar-toggler" data-toggle="collapse" data-target="#topNav">
			<span class="navbar-toggler-icon"></span>
		</button>
		<%-- <%=thisPage.equals("todo")?"active":"" --%>
		<%-- ${param.thisPage eq 'cafe' ? 'active' : '' } --%>
		<div class="collapse navbar-collapse" id="topNav">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item ${param.thisPage eq 'cafe' ? 'active' : '' }">
					<!-- 가급적이면 절대경로를 쓸 것. context 경로 걸기. cpath+tab -->
					<a class="nav-link" href="${pageContext.request.contextPath}/cafe/list.do">글 목록 보기</a>
				</li>
				<li class="nav-item ${param.thisPage eq 'file' ? 'active' : '' }">
					<!-- 가급적이면 절대경로를 쓸 것. context 경로 걸기. cpath+tab -->
					<a class="nav-link" href="${pageContext.request.contextPath}/file/list.do">자료실</a>
				</li>
				<li class="nav-item ${param.thisPage eq 'gallery' ? 'active' : '' }">
					<!-- 가급적이면 절대경로를 쓸 것. context 경로 걸기. cpath+tab -->
					<a class="nav-link" href="${pageContext.request.contextPath}/gallery/list.do">갤러리</a>
				</li>
			</ul>
			<c:choose>
				<c:when test="${empty sessionScope.id }"><%-- if(id==null) --%>
					<a class="btn btn-outline-success btn-sm" 
					href="${pageContext.request.contextPath }/users/loginform.do">로그인</a>
					<a class="btn btn-outline-danger btn-sm ml-1" 
					href="${pageContext.request.contextPath }/users/signup_form.do">회원가입</a>
				</c:when>
				<c:otherwise>
					<span class="navbar-text">
						<a href="${pageContext.request.contextPath }/users/private/info.do">${sessionScope.id }</a>
						<a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath }/users/logout.do">로그아웃</a>
					</span>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</nav>