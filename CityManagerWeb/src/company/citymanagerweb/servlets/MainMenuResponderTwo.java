package company.citymanagerweb.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainMenuResponderTwo
 */
@WebServlet("/mainmenurespondertwo")
public class MainMenuResponderTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainMenuResponderTwo() {
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
		doGet(request, response);
		String userChoice = request.getParameter("menuChoice");
		/*
		 * instead of building a query string, just set attributes to pass on to
		 * the next object (servlet or page)
		 */
		request.setAttribute("userChoice", userChoice);
		String[] userOptions = request.getParameterValues("adminoptions");

		// now we can pass the information without having to
		// show it in the query string...
		// request.setAttribute("useDB", 0);
		// request.setAttribute("sendEmail", 0);

		if (userOptions != null) {
			for (int i = 0; i < userOptions.length; i++) {

				if (userOptions[i].equalsIgnoreCase("useDB")) {
					request.setAttribute("useDB", "1");

				}
				if (userOptions[i].equalsIgnoreCase("sendEmail")) {
					request.setAttribute("sendEmail", "1");
				}
			}

		}
		// one other nice feature about attributes vs. parameters
		// is that we can send any object in the attribute, not
		// just a single string with a matching value as String.

		// however, HTML files can't read attributes, so let's
		// first send to an intermediary servlet
		// (in the future, we'll learn how we can access
		// java code like the attributes from a jsp page)
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mainmenuresponderhelper");
		dispatcher.forward(request, response);

	}

}
