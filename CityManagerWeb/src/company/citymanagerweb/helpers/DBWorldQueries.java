
package company.citymanagerweb.helpers;

/**
 * @author: bikeshkawan
 * @Date:   8 May 2016 
 * @Time: 22:00:08
 * @FileName: DBWorldQueries.java
 */
public class DBWorldQueries {

	
	/**
	 * 
	 */
	public DBWorldQueries() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getCitiesByDistrictByPopulation(){
		return "select * from city where CountryCode = 'FRA' order by District ASC, Population DESC";
		
	}
    
	public static String getCountiresByName(){
		return "select Code, Name,Population from country order by Name";
	}
}
