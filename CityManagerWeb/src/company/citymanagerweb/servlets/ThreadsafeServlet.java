package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThreadsafeServlet
 */
@WebServlet("/threadsafeservlet.do")
public class ThreadsafeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThreadsafeServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// synchronize the servlet context to avoid multiple resource writing
		// concurrently
		synchronized (getServletContext()) {

			// get the attribute and multiply by two to increase
			// factor but pretend this is a longer running procecss
			PrintWriter out = response.getWriter();
			StringBuilder msg = new StringBuilder("");
			msg.append("<html><body>");
			int currentSeed = (int) getServletContext().getAttribute("currentSeedValue");
			msg.append(String.format("Default Context Attribute retrived [currentSeed] = %d<br>", currentSeed));
			msg.append(String.format("Multipling current seed [currentSeed] by 2<br>"));
			currentSeed *= 2;
			msg.append(String.format("Set the Context attribute [currentSeedValue] to new value [currentSeed]= %d<br>",
					currentSeed));
			getServletContext().setAttribute("currentSeedValue", currentSeed);

			try {
				Thread.sleep(5000);// wait for 5 seconds
			} catch (InterruptedException e) {
			}
			// refetch the value after waiting 5 seconds

			msg.append(String.format("Fecting the Context Value attribute [currentSeedValue]  after setting <br>"));
			currentSeed = (int) getServletContext().getAttribute("currentSeedValue");
			msg.append(String.format("Attribute retrieved value after process: %d<br>", currentSeed));
			msg.append("</body></html>");
			
			out.println(msg);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
