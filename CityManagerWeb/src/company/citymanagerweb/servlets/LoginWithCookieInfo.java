package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bikeshkawan
 * @Date 7 May 2016
 * 
 * Servlet implementation class LoginWithCookieInfo
 */
@WebServlet({ "/LoginWithCookieInfo", "/loginwithcookieinfo.do" })
public class LoginWithCookieInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginWithCookieInfo() {
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
		doGet(request, response);
		
		String username = "";// if cookie is not set
		String password = "";
		
		//attemtp to pull information from a cookies
		//import javax.servlet.http.cookie;
		Cookie[] myCookies = request.getCookies();
		if(myCookies !=null){
			for(Cookie c: myCookies){
				if(c.getName().equalsIgnoreCase("credentials_username")){
					//set the uid from cookie value
					username = c.getValue();
					
				}else if(c.getName().equalsIgnoreCase("credentials_password")){
					
					// set the pqd from cookie
					password = c.getValue();
					
				}
			}
		}
		//build the page
				StringBuilder sb = new StringBuilder("");
				sb.append("<html><body>");
				sb.append("<form id='frmLogin' name='frmLogin' "
						+ "action='/CityManagerWeb/userlogincookies.do' "
						+ "method='post'>");
				sb.append("<table>");
				sb.append("<tr><td><span>Username:</span></td>"
						+ "<td><input type='text' name='username' value='" + username + "'/></td></tr>");
				sb.append("<tr><td><span>Password:</span></td>"
						+ "<td><input type='password' name='password'  value='" + password + "'/></td></tr>");
				sb.append("<tr><td colspan='2' align='right'>"
						+ "<input type='checkbox' name='remember' />Remember Me</td></tr>");
				sb.append("<tr><td colspan='2' align='right'>"
						+ "<input name='loginbutton' type='submit' value='Log In' />");
				sb.append("</table>");
				sb.append("</form>");
				sb.append("</body></html>");
		
				//spit out the login page:
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.println(sb.toString());
	}

}
