package commandline;
import java.sql.*;

/** 
 * Calculates the average number of draws per game
 * @author Rebecca Dinneen
 *<br> <br>
 *
 *  */

public class averageDraws {
	
	/**Method establishes database connection to PostgreSQL server
	 *@param url specifies location of database server and database name
	 *@param username database user name
	 *@param password database password
	 *@return Connection object
	 *@throws java.sql.SQLException
	 * */
	
	private final String url = "jdbc:postgresql://52.24.215.108:5432/MakeTrumpsGreatAgain";
	private final String username = "MakeTrumpsGreatAgain";
	private final String password = "MakeTrumpsGreatAgain";
	
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(url, username, password);	
	}
	/** Method selects no_draws column from 'Games' table
	 * @param rs ResultSet object represents database result set returned after query executed
	 *@returns count largest integer in column 
	 *@throws java.sql.SQLException */
	
	public int drawsAverage() {
		
		//String stores SQL query
		String SQL = "SELECT AVG (no_draws) FROM games";
		
		int count = 0;  //Stores results of query
		
		try (Connection conn = connect(); //Create Connection object
				Statement stmt = conn.createStatement(); //Statement object created from Connection object, Statement object represents SQL query
				ResultSet rs = stmt.executeQuery(SQL)) { //Statement object executed to get ResultSet object
				rs.next();								//next() method of ResultSet moves pointer to next row (1st row) from initial position (0)
				count = rs.getInt(1);					//Gets the value from ResultSet 
			}catch (SQLException e) {
				System.out.println(e.getMessage()); //Obtains JDBC driver's error message for an error handled by driver or Oracle error number and message for database error
			}
			return count;
	}

}
