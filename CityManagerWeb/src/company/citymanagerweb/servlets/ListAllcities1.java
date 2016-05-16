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
import company.citymanagerweb.models.MySqlServerConnectionBehavior;
import company.citymanagerweb.models.ServerConnectionBehavior;

/**
 * @author bikeshkawan
 * @Date 7 May 2016 Servlet implementation class ListAllcities
 */
@WebServlet("/listallcities1.do")
public class ListAllcities1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllcities1() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//generate the output in a StringBuilder
		StringBuilder sb = new StringBuilder("<html><body>");
		
	   //get the DBManager from context
		
		DBManager dbm = (DBManager) getServletContext().getAttribute("WorldDBManager");
		
		try
		{
			//connect to the db and open the connection
			if (!dbm.isConnected())
			{
				if (!dbm.openConnection()) 
				{
					//massive failure, log it
					sb.append("Could not connect to the database...");
				}
			}
			
			//ID NAME CountryCode District Population
			//get the cities into a table:
			sb.append("<table border=1>" 
						+ "<tr><td>ID</td><td>NAME</td><td>COUNTRY_CODE</td>"
						+ "<td>DISTRICT</td><td>POPULATION</td></tr>");
			
			//is this MVC? NO.  Queries should be logic outside of the controller.
			//views should handle the results.  We clearly have a way to go to
			//get ot MVC from here.
//			String query = "select * from city where CountryCode = 'FRA'" +
//							" order by District ASC, Population DESC";
			String query = DBWorldQueries.getCitiesByDistrictByPopulation();
			ResultSet rs = dbm.ExecuteResultSet(query);
			while (rs.next())
			{
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				String ctry = rs.getString("CountryCode"); 
				String dist = rs.getString("District"); 
				int pop = rs.getInt("Population");
				
				sb.append("<tr><td>" + id + "</td>" 
						+ "<td>" + name + "</td>" 
						+ "<td>" + ctry + "</td>" +
						"<td>" + dist + "</td>" +
						"<td>" + pop + "</td></tr>");
			}
			sb.append("</table>");
		}
		catch(Exception e)
		{
			sb.append("<h1>ERROR: " + e.getMessage() + "</h1>");
		}
		sb.append("</body></html>");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(sb);
	}
	

}
