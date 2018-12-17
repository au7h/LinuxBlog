<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Napisz wiadomość</title>
</head>
<body>
	<h2 style="color: red">${error}</h2>
	<form action="" method="post">
		<h2>Login odbiorcy:</h2>
		<input type="text" name="target" /><br />
		<h2>Treść wiadomości:</h2>
		<textarea name="content" rows="10" cols="95"></textarea>
		<br /> <input type="submit" value="Wyślij" />
	</form>
</body>
</html>