<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LinuxBlog - rejestracja</title>
<meta name="description"
	content="Kilka słów o systemie Linux. Ciekawostki ze świata programowania i nie tylko." />
<meta name="keywords"
	content="blog, system, linux, os, debian, ubuntu, ciekawostki, programowanie, www, programista, java" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/registerPage.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fontello_icons/fontello-0e2115ac/css/fontello.css"
	type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700"
	rel="stylesheet">
</head>
<body>
	<!-- Update captcha image script -->
	<script>
		window.onload = function() {
			var image = document.getElementById("captchaImg");

			function updateImage() {
				image.src = image.src.split("?")[0] + "?"
						+ new Date().getTime();
			}

			setInterval(updateImage, 1000);
		}
	</script>
	<div class="registerForm">
		<span style="color: red; font-size: 12px; letter-spacing: 1px;">${error}</span>
		<form action="" method="post" enctype="multipart/form-data">
			<div class="tile">
				<p>Imię:</p>
				<input name="firstName" />
				<p>Nazwisko:</p>
				<input name="lastName" />
				<p>Adres zamieszkania:</p>
				<input name="address" />
				<p>Data urodzenia:</p>
				<input type="date" name="birthDate">
			</div>
			<div class="tile">
				<p>Login:</p>
				<input name="login" />
				<p>Hasło:</p>
				<input type="password" name="password" />
				<p>Hasło (ponownie):</p>
				<input type="password" name="retypePassword" />
				<p>Adres e-mail:</p>
				<input name="email" />
			</div>
			<div id="tileEnd">
				<p>Avatar:</p>
				<input type="file" name="photo" id="file" style="border: none" />
				<p>Przepisz kod z obrazka:</p>
				<img id="captchaImg" src="stickyImg" /><br /> <input name="answer" /><br />
				<br /> <input type="submit" class="registerButton"
					value="Zarejestruj" /><br /> <a href="home" class="returnButton">Powrót</a>
			</div>
		</form>
	</div>
</body>
</html>