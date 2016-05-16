package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainMenuResponder
 */
@WebServlet("/mainmenuresponder")
public class MainMenuResponder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainMenuResponder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<h1>hellodfdsfsd</h1>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String userChoice = request.getParameter("menuChoice");
		out.println("<h1>Menu Choice: "+userChoice+"</h1>");
		String[] userOptions = request.getParameterValues("adminoptions");
		out.println("<ul>");
		for(String s:userOptions){
			out.println("<li>Options: "+s+"</li>");

		}
		out.println("</ul>");
		
		StringBuilder params = new StringBuilder("");
		String queryStringParams = "";
		
		if(userOptions !=null){
			boolean isFirst = true;
			for(int i =0; i<userOptions.length; i++){
				
				if(!isFirst){
					params.append("&");
				}else{
					params.append("?");
					
				}
				if(userOptions[i].equalsIgnoreCase("useDB")){
					params.append("userDB=1");
				}else if(userOptions[i].equalsIgnoreCase("sendEmail")){
					params.append("sendEmail=1");
					
				}
				isFirst = false;
			}
			
			queryStringParams = params.toString();
//			out.println("<h1> QuerryStringparams"+queryStringParams +"</h1>");
//			out.println("<h3>Main Menu Handler</h3>" 
//					+ "<p>User Choice: " + userChoice + "</p>");
		}
		if(userOptions == null){
			queryStringParams = "DSFdsf";
		}
		
		//instead of posting data, transfer to the correct option
				/*
				out.println("<html><body><h1>Main Menu Handler</h1>" 
							+ "<p>User Choice: " + userChoice + "</p></body></html>");
				*/
		//instead of posting data, transfer to the correct option
				/*
				out.println("<html><body><h1>Main Menu Handler</h1>" 
							+ "<p>User Choice: " + userChoice + "</p></body></html>");
				*/
				
				if (userChoice.equals("1")) 
				{
					response.sendRedirect("ListCities.html" + queryStringParams);
				}
				else if (userChoice.equals("2"))
				{
					response.sendRedirect("AddCity.html" + queryStringParams);
				}
				else if (userChoice.equals("3"))
				{
					response.sendRedirect("DeleteCity.html" + queryStringParams);
				}
				else
				{
					//invalid response
					
					response.sendRedirect("index.html");
				}
				

		
	}

}
