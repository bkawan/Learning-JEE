package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ThreadSafeRequest
 */
@WebServlet("/threadsaferequest.do")
public class ThreadSafeRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThreadSafeRequest() {
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
		out.println("Request Levels <br>");
		 //get the attribute and multiply by two to increase
		//factor but pretend this is a loger running process
		StringBuilder msg = new StringBuilder("");
		//msg.append("<html><body>");
		int currentSeed = (int) getServletContext().getAttribute("currentSeedValue");
		msg.append(String.format("\ncurrentSeed getServletContext() Attribute Retrived value: %d<br>", currentSeed));

		out.println(msg);
		currentSeed *=2;
		out.print(String.format("\ncurrentSeed Attribute  Retrived value multipled by 2: %d<br>", currentSeed));
		
		//set the value in the REQUEST
		out.println("\nSet request attribute to " + currentSeed + "<br>");
		request.setAttribute("currentSeedValue", currentSeed);//which is seed multiplied by 2
		
		//set the value in the context
		out.println("\ncontext value set to " + currentSeed + "<br>");

		getServletContext().setAttribute("currentSeedValue", currentSeed);//which is multiplied by 2
		
		int currentSeed1=(int)request.getAttribute("currentSeedValue");
		int currentSeed2 = (int)getServletContext().getAttribute("currentSeedValue");
		
		out.println("\n1st Request Level: " + currentSeed1 +"<br> 1st Context Level " + currentSeed2 + "<br>" );
		
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e){
			out.println(e.getMessage());
		}
		//update the context
		out.println("\nUpdate the context value  to " + currentSeed + "<br>");
		getServletContext().setAttribute("currentSeedValue", currentSeed );
		
		//re fetch the value after waiting from the request
		
		currentSeed1 = (int) request.getAttribute("currentSeedValue");
		out.println("\n2nd Re fetch the request Value: " + currentSeed1 + "<br>");
		
		
		//re fetch the value after waiting from the Context
		currentSeed2 = (int) getServletContext().getAttribute("currentSeedValue");
		out.println("\n2nd Re fetch the Context Value: " + currentSeed2 + "<br>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
