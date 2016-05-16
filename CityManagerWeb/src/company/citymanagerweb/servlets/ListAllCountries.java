package company.citymanagerweb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import company.citymanagerweb.helpers.DBWorldQueries;
import company.citymanagerweb.models.DBManager;

/**
 * @author bikeshkawan
 * @Date 8 May 2016
 * Servlet implementation class ListAllCountries
 */
@WebServlet("/ListAllCountries")
public class ListAllCountries extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllCountries() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the DBManager from context
		DBManager dbm = (DBManager) getServletContext().getAttribute("WorldDBManager");
		
		StringBuilder sb = new StringBuilder("<html><body>");
		
		try {
			// connect to the db and open connection
			if(!dbm.isConnected()){
				if(!dbm.openConnection()){
					//faliure
					sb.append("Couldn't be connect to the databse");
				}
			}
			//Code Name Population
			//get the cities into a table:
			sb.append("<table border=1>" 
						+ "<tr><td>CODE</td><td>NAME</td><td>POPULATION</td></tr>");
			String query = DBWorldQueries.getCountiresByName();
			ResultSet rs = dbm.ExecuteResultSet(query);
			while(rs.next()){
				String id = rs.getString("CODE");
				String name = rs.getString("NAME");
				int pop = rs.getInt("POPULATION");
				
				sb.append("<tr><td>" + id + "</td>" 
						+ "<td>" + name + "</td>" 
						+ "<td>" + pop + "</td></tr>");
			}
			sb.append("</table>");
			
		} catch (Exception e) {
			sb.append("<h1>ERROR: " + e.getMessage() + "</h1>");
		}
		sb.append("</body></html>");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(sb);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
