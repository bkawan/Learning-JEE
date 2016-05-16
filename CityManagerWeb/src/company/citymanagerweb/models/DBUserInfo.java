
package company.citymanagerweb.models;

/**
 * @author: bikeshkawan
 * @Date:   7 May 2016 
 * @Time: 11:53:21
 * @FileName: DBUserInfo.java
 */
public abstract class DBUserInfo {
	private String username;
	private String password;
	private String dbName;
	

	/**
	 * 
	 */
	public DBUserInfo() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param username
	 * @param password
	 * @param dbName
	 */
	public DBUserInfo(String username, String password, String dbName) {
		this.username = username;
		this.password = password;
		this.dbName = dbName;
	}


	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}


	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}


	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}


	/**
	 * @param dbName the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
 

}
