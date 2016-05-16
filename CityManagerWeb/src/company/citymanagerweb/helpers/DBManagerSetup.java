package company.citymanagerweb.helpers;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import company.citymanagerweb.models.DBManager;
import company.citymanagerweb.models.MySqlServerConnectionBehavior;
import company.citymanagerweb.models.ServerConnectionBehavior;

/**
 * @author bikeshkawan
 * @Date 8 May 2016
 * Application Lifecycle Listener implementation class DBManagerSetup
 *
 */
@WebListener
public class DBManagerSetup implements ServletContextListener {

	private DBManager dbm = null;
    /**
     * Default constructor. 
     */
    public DBManagerSetup() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	//clean up the connection when the context is destroyed
    	if(dbm !=null){
    		if(dbm.isConnected()){
    			dbm.closeConnection(false);
    		}
    	}
    	dbm = null;
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	//access the servlet context from the event argument 
    	//get db connection inf context init params
    	ServletContext sc = sce.getServletContext();
    	String username = sc.getInitParameter("dbusername");
    	String password = sc.getInitParameter("dbpassword");
    	String dbName = sc.getInitParameter("dbnameinit");
    	
    	//set the scb for mysql
    	ServerConnectionBehavior scb = new MySqlServerConnectionBehavior(username,password,dbName);
    	
    	//create the manager
    	
    	dbm = new DBManager(scb);
    	
    	//put the manager into the servlet context attributes for global use in the application
    	sc.setAttribute("WorldDBManager", dbm);
    	System.out.println("WorldDBManager created and added to context");
    }
    
	
}
