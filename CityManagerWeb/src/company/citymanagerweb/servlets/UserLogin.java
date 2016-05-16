package company.citymanagerweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet({ "/UserLogin", "/userlogin.do" })
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// form request is post
		// Here we put some logic to validate user from database
		
				//String uid = "authorizeduser";
				//int authLevel = 2;
				String uid = request.getParameter("username");
				int authLevel = Integer.parseInt(request.getParameter("userLevel"));
				
				
				
				// assuming the user is valid , lets set some information into session
				//in this way all jsp pages and servlets going forward can access the value 
				
				// to start a session get the session from the request into ta variable 
				// and import javax.servlet.http.HttpSession;
				HttpSession  session = request.getSession();
				// just like other examples, use the session variable to get and set attributes
				session.setAttribute("username", uid);
				session.setAttribute("userAuthLevel", authLevel);
				
				//redirect
				
				if(authLevel < 1 || uid == null || uid.isEmpty()){
					
					//send back to calling page or forward to unauthorized notification
					// if cookie is disable this will not work
					response.sendRedirect("login.html");
					//if cookie is disable it will work
					//response.sendRedirect(response.encodeRedirectURL("login.html"));
			
				}else{
					// forward to requested page or menu page  home page with authorization
					// if cookie is disable this will not work
					response.sendRedirect("/CityManagerWeb/destinationpage.do");
					
					//if cookie is disable it will work
					//response.sendRedirect(response.encodeRedirectURL("/CityManagerWeb/destinationpage.do"));
				}
	}

}
