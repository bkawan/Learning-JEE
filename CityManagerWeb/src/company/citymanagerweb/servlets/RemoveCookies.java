package company.citymanagerweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bikeshkawan
 * @Date 7 May 2016 Servlet implementation class RemoveCookies
 */
@WebServlet({ "/RemoveCookies", "/removecookies.do", "/removecookies" })
public class RemoveCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveCookies() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		Cookie[] myCookies = request.getCookies();

		if (myCookies != null && myCookies.length > 0) {
			for (Cookie c : myCookies) {
				if (c.getName().toLowerCase().contains("credentials")) {
					// expire the cookies
					c.setMaxAge(0);
					response.addCookie(c);
				}

			}
		}
		response.getWriter().println("<html><body>Cookie Expired!!</body></html>");

		
	}

}
