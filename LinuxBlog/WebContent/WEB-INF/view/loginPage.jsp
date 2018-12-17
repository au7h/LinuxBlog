<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LinuxBlog - logowanie</title>
<meta name="description"
	content="Kilka słów o systemie Linux. Ciekawostki ze świata programowania i nie tylko." />
<meta name="keywords"
	content="blog, system, linux, os, debian, ubuntu, ciekawostki, programowanie, www, programista, java" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/loginPage.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fontello_icons/fontello-0e2115ac/css/fontello.css"
	type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700"
	rel="stylesheet">
</head>
<body>
	<div class="loginImage">
		<img
			src="${pageContext.request.contextPath}/images/loginHeaderImage.png" />
	</div>
	<div class="loginForm">
		<span style="color: red; font-size: 12px; letter-spacing: 1px;">${error}</span>
		<form action="" method="post">
			<span style="font-size: 16px; font-weight: 700; letter-spacing: 1px">Login:</span><br />
			<input class="loginField" name="user" /><br /> <span
				style="font-size: 16px; font-weight: 700; letter-spacing: 1px">Hasło:</span><br />
			<input class="passField" type="password" name="pass" /><br /> <input
				class="submitButton" type="submit" value="Zaloguj" /> <a
				href="home" class="returnButton">Powrót</a>
		</form>
	</div>
</body>
</html>