<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/users/private/update.jsp</title>
</head>
<body>
<script>
	alert("수정 했습니다.");
	/*간접 리다일렉트 효과 - 리다일렉트 하기 전에 약간 동작을 할 수 있다. (알림창을 띄운다.)*/
	location.href="${pageContext.request.contextPath }/users/private/info.do";
</script>	
</body>
</html>




