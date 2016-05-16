package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NonThreadSafeServlet
 */
@WebServlet("/nonthreadsafeservlet.do")
public class NonThreadSafeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NonThreadSafeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// get the attribute and multiply by two to increase
		// factor but pretend this ia longer running process
		PrintWriter out = response.getWriter();

		StringBuilder msg = new StringBuilder("");
		msg.append("<html><body>");
		int currentSeed = (int) getServletContext().getAttribute("currentSeedValue");// 2
		msg.append("Getting default CONTEXT attribute..[currentSeed]........<br>");
		msg.append(String.format("\nContext Attribute received (getServletContext() = %d<br>", currentSeed));
		msg.append("Setting CONTEXT  valuve [currentSeed] begin.......<br>");
		currentSeed *= 2;
		msg.append(" CONTEXT value [currentSeed] changed to currentSeed x 2.......<br>");
		msg.append(String.format("Setting CONTEXT value [currentSeed] to: %d<br>" ,currentSeed));
		getServletContext().setAttribute("currentSeedValue", currentSeed);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}

		// re-fetching the value after waiting
		currentSeed = (int) getServletContext().getAttribute("currentSeedValue");
		msg.append(String.format("Attribute retrieved value after process: %d<br>", currentSeed));

		msg.append("</body></html>");

		out.println(msg);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
