
package company.citymanagerweb.models;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author: bikeshkawan
 * @Date: 7 May 2016
 * @Time: 16:04:02
 * @FileName: MySqlServerConnectionBehaviour.java
 */
public class MySqlServerConnectionBehavior extends DBUserInfo implements ServerConnectionBehavior {
	public MySqlServerConnectionBehavior() {
		super();
	}

	public MySqlServerConnectionBehavior(String username, String password, String dbName) {
		super(username, password, dbName);
	}

	@Override
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection cn = DriverManager.getConnection(getConnectionURL());
			return cn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getConnectionURL() {
		return String.format("jdbc:mysql://localhost/%s" + "?user=%s&password=%s", getDbName(), getUsername(),
				getPassword());
	}

	@Override
	public String getConnectionDetails() {
		return "MySQL Database Connection to " + getDbName();
	}

	@Override
	public String getTablesSchemaQuery() {
		return "select table_name from information_schema.tables " + "where table_schema = " + getDbName();
	}
}
