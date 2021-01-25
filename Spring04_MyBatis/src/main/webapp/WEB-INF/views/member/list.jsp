<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/list.jsp</title>
</head>
<body>
<div class="container">
	<a href="insertform.do">회원추가하러 가기</a>
	<h1>회원 목록 입니다.</h1>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>주소</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tmp" items="${list }"><!-- requestScope 생략 가능 -->
				<tr>
					<td>${tmp.num }</td>
					<td>${tmp.name }</td>
					<td>${tmp.addr }</td>
					<td><a href="updateform.do?num=${tmp.num }">수정</a></td>
					<td><a href="javascript:deleteConfirm(${tmp.num })">삭제</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script>
	function deleteConfirm(num){
		let isDelete=confirm(num+" 번 회원을 삭제 하시겠습니까?");
		if(isDelete){
			location.href="delete.do?num="+num;
		}
	}
</script>
</body>
</html>