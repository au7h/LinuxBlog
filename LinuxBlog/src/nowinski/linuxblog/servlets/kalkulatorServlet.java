package nowinski.linuxblog.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class kalkulatorServlet
 */
@WebServlet("/kalkulator")
public class kalkulatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/kalkulator.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int liczbaA = 0;
		int liczbaB = 0;
		String dzialanie = "";
		try {
			liczbaA = Integer.parseInt(request.getParameter("liczbaA"));
			liczbaB = Integer.parseInt(request.getParameter("liczbaB"));
			dzialanie = request.getParameter("dzialanie");
		} catch (Exception ex) {
			request.setAttribute("error", "Nie wybrano liczb");
			doGet(request, response);
			return;
		}
		double wynik = 0.0;
		switch (dzialanie) {
		case "dodaj":
			wynik = suma(liczbaA, liczbaB);
			break;
		case "odejmij":
			wynik = roznica(liczbaA, liczbaB);
			break;
		case "pomnoz":
			wynik = mnozenie(liczbaA, liczbaB);
			break;
		case "podziel":
			wynik = dzielenie(liczbaA, liczbaB);
			break;
		case "pierwiastek":
			wynik = pierwiastek(liczbaA);
			break;
		case "potega":
			wynik = potega(liczbaA, liczbaB);
			break;
		}
		request.setAttribute("wynik", wynik);
		doGet(request, response);
	}

	public double suma(double a, double b) {
		return a + b;
	}

	public double roznica(double a, double b) {
		return a - b;
	}

	public double mnozenie(double a, double b) {
		return a * b;
	}

	public double dzielenie(double a, double b) {
		return a / b;
	}

	public double pierwiastek(double a) {
		return Math.sqrt(a);
	}

	public double potega(double a, double b) {
		return Math.pow(a, b);
	}

}
