package company.citymanagerweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author bikeshkawan
 * @Date 7 May 2016 Servlet implementation class UserLoginCookies
 */
@WebServlet({ "/UserLoginCookies", "/userlogincookies.do", "/userlogincookies" })
public class UserLoginCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginCookies() {
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
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// pull the values from the post Variables from form

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean remember = false;

		if (request.getParameter("remember") != null) {
			String rememberMe = request.getParameter("remember");
			if (rememberMe.equalsIgnoreCase("on")) {
				remember = true;
			}
		}
		// here we would put some logic to validate the user
		//simulate...
		int authLevel = 1;
		
		// to start a session, get the session from the request into variable
		//add import javax.servlet.http.HttpSession;
		
		HttpSession session = request.getSession();
		// set the session attributes
		session.setAttribute("username", username);
		session.setAttribute("userAuthLevel", authLevel);
		
		if(remember){
			//store information in a cookie
			
			int cookieLife = 3600*24*7; //7 days
			Cookie usernameCookie = new Cookie("credentials_username", username);
			usernameCookie.setMaxAge(cookieLife);
			response.addCookie(usernameCookie);
			Cookie passwordCookie = new Cookie("credentials_password", password);
			passwordCookie.setMaxAge(cookieLife);
			response.addCookie(passwordCookie);
		}
		
		//redirect
		
		if(authLevel < 1 || username== null || username.isEmpty()){
			
			//send back to calling page or forward to unauthorized notification
			
			response.sendRedirect(response.encodeRedirectURL("index_cookies.html"));
		}else{
			response.sendRedirect(response.encodeRedirectURL("/CityManagerWeb/destinationpage.do"));
		}

	}

}
