<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 style="color: red">Lập trình java 3</h1>
	
	<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>

	<h1>Protocol: ${pro}</h1>
	<h1>Context Path: ${ctxPath}</h1>
	<h1>Họ và tên: ${user.name}</h1>
	<h1>Vai trò: ${user.role == 0 ? 'User' : 'Admin'}</h1>
	<h1>Họ và tên user 1: ${users[0].name}</h1> <!-- Mảng thông thường -->
	<%-- <h1>Họ và tên user 1: ${usersArr.get(0).name}</h1> <!-- Array List --> --%>
</body>
</html>