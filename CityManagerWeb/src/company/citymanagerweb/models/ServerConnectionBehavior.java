
package company.citymanagerweb.models;

import java.sql.Connection;

/**
 * @author: bikeshkawan
 * @Date:   7 May 2016 
 * @Time: 11:48:30
 * @FileName: ServerConnectionBehavior.java
 * allows poymorphic database connections. 
 */
public interface ServerConnectionBehavior {
	
	Connection getConnection();
	String getConnectionURL();
	String getConnectionDetails();
	String getTablesSchemaQuery();
	

}
