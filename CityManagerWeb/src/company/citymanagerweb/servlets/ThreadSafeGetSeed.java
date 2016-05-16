package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThreadSafeGetSeed
 */
@WebServlet("/threadsafegetseed.do" )
public class ThreadSafeGetSeed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadSafeGetSeed() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("Hello");
		out.println(request.getParameter("threadsafetyselection"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter out = response.getWriter();
		
		
		// retrieve the form parameter from the user selectin
		String doThreadSafe = request.getParameter("threadsafetyselection");
		//out.println(doThreadSafe);
////		//retrieve the context parameter for initial seed from web.xml
//		// it is available through out the application 
		int initialSeed = Integer.parseInt(getServletContext().getInitParameter("initialseed"));
////		
////		//seed the servlet context attribute for current seed
		getServletContext().setAttribute("currentSeedValue", initialSeed);
//		
	//determine where to direct traffice
		if(doThreadSafe.equalsIgnoreCase("requestthread")){

			response.sendRedirect("threadsaferequest.do");
		}
		else if(doThreadSafe.equalsIgnoreCase("nonthreadsafe")){
			
			response.sendRedirect("nonthreadsafeservlet.do");
		}
		else if(doThreadSafe.equalsIgnoreCase("threadsafe")){
			
			response.sendRedirect("threadsafeservlet.do");
		}
		
		else{
			response.sendRedirect("www.google.com");
//
		}
		
//		
}

}
