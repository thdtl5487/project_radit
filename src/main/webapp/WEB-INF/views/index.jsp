<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인페이지</title>
</head>
<body>
	<form action="payment.do" method="get">
				<input type="hidden" name="id" value="<%= request.getParameter("id") %>"/>
				<input type="hidden" name="title" value="<%= request.getParameter("email") %>"/>
				<input type="hidden" name="content" value="<%= request.getParameter("name") %>"/>
				<input type="submit" value="수정하기">
			</form>
	<a href="payment.do">[결제하기]</a>
</body>
</html>
