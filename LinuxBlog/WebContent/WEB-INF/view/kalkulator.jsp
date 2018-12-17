<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="" method="post">
	<h1 style="color: red">${error }</h1>
		Wpisz 1 liczbę: <input type="text" name="liczbaA" /><br />
		<br /> Wpisz 2 liczbę: <input type="text" name="liczbaB" /><br />
		<input type="radio" name="dzialanie" value="dodaj"> Dodawanie<br />
		<input type="radio" name="dzialanie" value="odejmij"> Odejmij<br />
		<input type="radio" name="dzialanie" value="pomnoz"> Pomnóż<br />
		<input type="radio" name="dzialanie" value="podziel"> Podziel<br />
		<input type="radio" name="dzialanie" value="pierwiastek"> Pierwiastek<br />
		<input type="radio" name="dzialanie" value="potega"> Potega<br />
		<input type="submit" value="Oblicz" />
		<h1>Wynik: ${wynik}</h1>
	</form>
</body>
</html>