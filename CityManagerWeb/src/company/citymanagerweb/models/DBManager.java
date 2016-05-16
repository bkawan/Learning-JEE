
package company.citymanagerweb.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author: bikeshkawan
 * @Date: 7 May 2016
 * @Time: 16:35:16
 * @FileName: DBManager.java
 */
public class DBManager implements Serializable {

	Connection conn = null;
	ServerConnectionBehavior scb = null;
	
	public DBManager()
	{
		
	}
	
	public DBManager(ServerConnectionBehavior conBehavior)
	{
		scb = conBehavior;
	}
	
	public boolean setConnectionBehavior(ServerConnectionBehavior value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("Please use a valid connection behavior");
		}
		scb = value;
		return true;
	}
	
	public boolean openConnection()
	{
		try
		{
			if (scb == null)
			{
				throw new IllegalArgumentException("Define "
						+ "a connection behavior");
			}
			if (conn != null) closeConnection(false);
			conn = scb.getConnection();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		if (conn == null) return false;
		return true;
	}

	public boolean closeConnection(boolean keepAlive)
	{
		try
		{
			if (conn != null)
			{
				if (!conn.isClosed())
				{
					conn.close();
				}
			}
			if (!keepAlive)
			{
				conn = null;
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isConnected()
	{
		return conn != null; 
	}
	
	public boolean ExecuteNonQuery(String query)
	{
		try
		{
			Statement st = conn.createStatement();
			int i = st.executeUpdate(query);
			if (i == -1)
			{
				//log it
				return false;
			}
			st.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ResultSet ExecuteResultSet(String query) throws SQLException
	{
		PreparedStatement st = conn.prepareStatement(query);
		ResultSet rs = st.executeQuery();
		return rs;
	}
	
	public Connection getConnection()
	{
		return conn;
	}

	public String getConnectionURL()
	{
		return scb.getConnectionURL();
	}
	
	public String getTablesSchemaQuery()
	{
		return scb.getTablesSchemaQuery();
	}

}
