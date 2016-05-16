
package company.citymanagerweb.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.net.httpserver.HttpsConfigurator;

/**
 * Servlet implementation class AuthorizedUser
 */
@WebServlet({ "/AuthorizedUser", "/authorizeduser" })
public class AuthorizedUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorizedUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		boolean authorizedUser = false;
		
		String uid = null;
		int authLevel = 0;
		
		if(session.getAttribute("username") !=null){
			uid = (String) session.getAttribute("username");
		}if(session.getAttribute("userAuthLevel")!=null){
			authLevel = (int) session.getAttribute("userAuthLevel");
		}
		if(authLevel >=2 && uid != null && !uid.isEmpty()){
			authorizedUser = true;
		}
		if(!authorizedUser){
			response.sendRedirect("login.html");
			//response.sendRedirect(response.encodeRedirectUrl("login.html"));
		}else{
			response.sendRedirect("/CityManagerWeb/destinationpage");
			//response.sendRedirect(response.encodeRedirectUrl("login.html"));

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
