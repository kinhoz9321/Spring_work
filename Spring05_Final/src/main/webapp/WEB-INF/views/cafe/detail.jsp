<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/cafe/detail.jsp</title>
<style>
	/* 프로필 이미지를 작은 원형으로 만든다 */
	#profileImage{
		width: 50px;
		height: 50px;
		border: 1px solid #cecece;
		border-radius: 50%;
	}
	/* ul 요소의 기본 스타일 제거 */
	.comments ul{
		padding: 0;
		margin: 0;
		list-style-type: none;
	}
	.comments dt{
		margin-top: 5px;
	}
	.comments dd{
		margin-left: 26px;
	}
	.comment_form textarea, .comment_form button, 
		.comment-insert-form textarea, .comment-insert-form button{
		float: left;
	}
	.comments li{
		clear: left;
	}
	.comments ul li{
		border-top: 1px solid #888;
	}
	.comment_form textarea, .comment-insert-form textarea{
		width: 85%;
		height: 100px;
	}
	.comment_form button, .comment-insert-form button{
		width: 15%;
		height: 100px;
	}
	/* 댓글에 댓글을 다는 폼은 일단 숨긴다. */
	.comments form{
		display: none;
	}
	/* .reply_icon 을 li 요소를 기준으로 배치 하기 */
	.comments li{
		position: relative;
	}
	.comments .reply_icon{
		position: absolute;
		top: 1em;
		left: 1em;
		color: red;
	}
</style>
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
				<a href="${pageContext.request.contextPath }/cafe/list.do">글목록</a>
			</li>
			<li class="breadcrumb-item active">상세보기</li>
		</ul>
	</nav>	
	<table class="table table-bordered">
		<tr>
			<th>글번호</th>
			<td>${dto.num}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.writer}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${dto.viewCount}</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${dto.regdate}</td>
		</tr>
		<tr>
			<td colspan="2">
				<div>${dto.content}</div>
			</td>
		</tr>
	</table>
	<ul>
		<li><a href="list.do">목록보기</a></li>
		<c:if test="${requestScope.dto.writer eq sessionScope.id }">
			<li><a href="private/updateform.do?num=${dto.num }">수정</a></li>
			<li><a href="javascript:deleteConfirm()">삭제</a></li>
		</c:if>
	</ul>
	<!-- 댓글 목록 -->
	<div class="comments">
		<ul>
			<c:forEach var="tmp" items="${commentList }">
				<li <c:if test="${tmp.num ne tmp.comment_group }">style="padding-left:50px;"</c:if>>
					<c:if test="${tmp.num ne tmp.comment_group }"><svg class="reply_icon" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-arrow-return-right" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
  						<path fill-rule="evenodd" d="M10.146 5.646a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L12.793 9l-2.647-2.646a.5.5 0 0 1 0-.708z"/>
  						<path fill-rule="evenodd" d="M3 2.5a.5.5 0 0 0-.5.5v4A2.5 2.5 0 0 0 5 9.5h8.5a.5.5 0 0 0 0-1H5A1.5 1.5 0 0 1 3.5 7V3a.5.5 0 0 0-.5-.5z"/></svg>
					</c:if>				
					<dl>
						<dt>
						<c:choose>
							<c:when test="${empty tmp.profile }">
								<svg id="profileImage" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
					  				<path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
								</svg>
							</c:when>
							<c:otherwise>
								<img id="profileImage" 
									src="${pageContext.request.contextPath }${tmp.profile}" />
							</c:otherwise>
						</c:choose>	
						<span>${tmp.writer }</span>
							<c:if test="${tmp.num ne tmp.comment_group }">
								@<strong>${tmp.target_id }</strong>
							</c:if>
							<span>${tmp.regdate }</span>
							<a href="javascript:" class="reply_link">답글</a>		
						</dt>
						<dd>
							<pre>${tmp.content }</pre>
						</dd>
					</dl>
					<form class="comment-insert-form" 
						action="private/comment_insert.do" method="post">
						<input type="hidden" name="ref_group"
							value="${dto.num }"/>
						<input type="hidden" name="target_id"
							value="${tmp.writer }"/>
						<input type="hidden" name="comment_group"
							value="${tmp.comment_group }"/>
						<textarea name="content"></textarea>
						<button type="submit">등록</button>
					</form>					
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- 원글에 댓글을 다는 폼 -->
	<div class="comment_form">
		<form action="private/comment_insert.do" method="post">
			<!-- 원글의 글번호가 ref_group  번호가 된다. -->
			<input type="hidden" name="ref_group" value="${dto.num }" />
			<!-- 원글의 작성자가 댓글의 수신자가 된다. -->
			<input type="hidden" name="target_id" value="${dto.writer }"/>
			<textarea name="content"><c:if test="${empty id }">로그인이 필요합니다</c:if></textarea>
			<button type="submit">등록</button>
		</form>
	</div>
</div>
<script>
	//답글 달기 링크를 클릭했을때 실행할 함수 등록
	$(".reply_link").on("click", function(){
		//로그인 여부
		var isLogin=${not empty id};
		if(isLogin == false){
			alert("로그인 페이지로 이동합니다.")
			location.href="${pageContext.request.contextPath }/users/loginform.do?"+
					"url=${pageContext.request.contextPath }/cafe/detail.do?num=${dto.num}";
		}
		
		$(this).parent().parent().parent().find(".comment-insert-form")
		.slideToggle();
		if($(this).text()=="답글"){//링크 text를 답글일때 클릭하면 
			$(this).text("취소");//취소로 바꾸고 
		}else{//취소일때 크릭하면 
			$(this).text("답글");//답들로 바꾼다.
		}	
	});
	$(".comment_form form").on("submit", function(){
		//로그인 여부
		var isLogin=${not empty id};
		if(isLogin == false){
			alert("로그인 페이지로 이동합니다.")
			location.href="${pageContext.request.contextPath }/users/loginform.do?"+
					"url=${pageContext.request.contextPath }/cafe/detail.do?num=${dto.num}";
			return false; //폼 전송 막기 		
		}
	});
	function deleteConfirm(){
		let isDelete=confirm("글을 삭제 하시겠습니까?");
		if(isDelete){
			location.href="private/delete.do?num=${dto.num}";
		}
	}
</script>
</body>
</html>








