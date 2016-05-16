package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class DestinationPage
 */
@WebServlet({ "/destinationpage", "/destinationpage.do"})
public class DestinationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DestinationPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		out.println("Destination page: ");
//		String username = (String) session.getAttribute("username");
//		int authLevel = 1;
//		out.print(username +" "+ authLevel); 
		if(session !=null && !session.isNew() && session.getAttribute("username") !=null){
			out.println("Login Successfull: (true) <br>");
			
			String uid = (String) session.getAttribute("username");
			int authId  = (int) session.getAttribute("userAuthLevel");
		out.println(String.format("<html><body>Welcome  <b> %s </b>!!<br>Your are level %d user", uid, authId));
		}else{
			//out.println("Might be cookie is disable");
			out.println("False <br");

			out.println("<html><body><span>Unauthorized<span></body></html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
