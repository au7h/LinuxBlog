<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Moje wiadomości</title>
</head>
<body>
<h2 style="color: red">${error}</h2>
	<c:forEach var="message" items="${messages}">
		<h2>Autor wiadomości:</h2> ${message.user.login}
		<h2>Treść:</h2> ${message.content}
		<br />
	</c:forEach>
</body>
</html>