<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LinuxBlog - Strona główna</title>
<meta name="description"
	content="Kilka słów o systemie Linux. Ciekawostki ze świata programowania i nie tylko." />
<meta name="keywords"
	content="blog, system, linux, os, debian, ubuntu, ciekawostki, programowanie, www, programista, java" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/styles/main.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/fontello_icons/fontello-0e2115ac/css/fontello.css"
	type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700"
	rel="stylesheet">
</head>
<body>
	<header>
		<div id="header">
			<div id="h_logo">
				<img src="${pageContext.request.contextPath}/images/linux_logo.png" />
				<span style="color: #c34f4f">Linux</span>- blog
				<c:if test="${sessionScope.user.login eq null }">
					<div id="loginOrRegister">
						<a href="login" class="loginOrRegisterBtn">Logowanie</a> <a
							href="register" class="loginOrRegisterBtn">Rejestracja</a>
					</div>
				</c:if>
				<c:if test="${sessionScope.user.login ne null }">
					<div class="welcome_msg">
						<p>
							Witamy ${sessionScope.user.login }<i class="demo-icon icon-user"
								style="margin-left: 5px; color: white"></i>
						</p>
					</div>
					<div class="logged_view">
						<c:if test="${sessionScope.role.roleName eq 'admin'}">
							<a href="addThread">Dodaj wpis</a>
						</c:if>
						<a href="myProfile">Mój profil</a> <a href="editProfile">Edytuj
							dane</a> <a href="messages">Wiadomości</a> <a href="myTopics">Moje
							tematy</a> <a href="logout">Wyloguj się</a>
					</div>
				</c:if>
			</div>
			<nav>
				<ul class="menu">
					<li><a href="home">Strona główna</a></li>
					<li><a href="news">Lista tematów</a></li>
					<li><a href="search">Szukaj tematu</a></li>
					<li><a href="users">Użytkownicy</a></li>
					<li><a href="contact">Kontakt</a></li>
				</ul>
			</nav>
		</div>
	</header>
	<div id="content">
		<div id="c_center">
			<section>
				<div class="c_header">INFORMACJE O UŻYTKOWNIKU</div>
				<div class="about_user">
					<p>Avatar:</p>
					<img src="imageServlet?type=user&id=${userObj.id}" />
					<p>Login: ${userObj.login}</p>
					<p>Imię: ${userObj.name}</p>
					<p>Nazwisko: ${userObj.surname}</p>
					<p>Data urodzenia: ${userObj.birthDate}</p>
					<p>Adres zamieszkania: ${userObj.address}</p>
					<p>Adres e-mail: ${userObj.email}</p>
					<p>Data rejestracji: ${userObj.registerDate}</p>
					<p>
						Utworzone posty:
						<c:forEach var="userThread" items="${userThreads}">
							<p>${userThread.topic}</p>
						</c:forEach>
					</p>
				</div>
			</section>
		</div>
		<div id="c_right">
			<aside>
				<div class="social_topic">SOCIAL MEDIA</div>
				<div class="social_content">
					<div class="tileGit">
						<i class="demo-icon icon-github-circled"></i>
					</div>
					<div class="tileFb">
						<i class="demo-icon icon-facebook-squared"></i>
					</div>
					<div class="tileGp">
						<i class="demo-icon icon-gplus-circled"></i>
					</div>
				</div>
			</aside>
			<aside>
				<div class="cat_topic">KATEGORIE</div>
				<div class="cat_content">
					<a href="/php">html</a> <a href="/css">css</a> <a href="/java">java</a>
					<a href="/cpp">c/c++</a>
				</div>
			</aside>
			<aside>
				<div class="tag_topic">TAGI</div>
				<div class="tag_content">
					<p>aaaaaaaaa</p>
				</div>
			</aside>
			<aside>
				<div class="cal_topic">KALENDARZ</div>
				<div class="cal_content">
					<p>aaaaaaaaa</p>
				</div>
			</aside>
		</div>
	</div>
	<footer>
		<div id="footer">
			<p>Copyright &copy; 2018 LinuxBlog. Designed by Kamil Nowiński.
				Wszelkie prawa zastrzeżone.</p>
			<br />
			<p>Strona korzysta z plików cookies zgodnie z polityką
				prywatności.</p>
		</div>
	</footer>
</body>
</html>