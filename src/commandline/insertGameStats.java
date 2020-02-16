package commandline;
import java.sql.*;

/** 
 * Inserts game data into Games table
 * @author Rebecca Dinneen
 *<br> <br>
 *
 *  */

public class insertGameStats {
	
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
	
	/** Method inserts no_rounds,no_draws,winner into Games table
	 * @param rs ResultSet object represents database result set returned after query executed
	 *@returns count largest integer in column 
	 *@throws java.sql.SQLException */
	
	public long insert(GameStats g1) { 	//GameStats object passed to insert method
		
		//String stores SQL query
		String SQL = "INSERT INTO games(no_rounds,no_draws,winner)" + "VALUES(?,?,?)"; 
		
		long count = 0;
		
		try (Connection conn = connect(); //Create Connection object
				PreparedStatement pstmt = conn.prepareStatement(SQL, //PreparedStatement object created from Connection object, PreparedStatement object represents SQL query
				Statement.RETURN_GENERATED_KEYS)) { //Passed to PreparedStatement object
			
			pstmt.setInt(1, g1.getRounds()); //setInt method called on PreparedStatement object, getRounds called on game object to get value for insertion into game table, value inserted into index 1
			pstmt.setInt(2, g1.getDraws()); //setInt method called on PreparedStatement object, getDraws called on game object to get value for insertion into game table, value inserted into index 2
			pstmt.setString(3, g1.getWinner());  //setInt method called on PreparedStatement object, getWinner called on game object to get value for insertion into game table, value inserted into index 3
	
			
			int affectedRows = pstmt.executeUpdate(); //excuteUpdate method called on PreparedStatement object
			
			if(affectedRows>0) { 
				
				try(ResultSet rs = pstmt.getGeneratedKeys()) { //ResultSet object created, getGenerateKeys method called on PreparedStatement 
					if(rs.next()) { //next() method of ResultSet moves pointer to next row (1st row) from initial position (0)
						count = rs.getLong(1); //Gets the value from ResultSet 
					}
					
				}catch (SQLException e) {
					System.out.println(e.getMessage()); //Obtains JDBC driver's error message for an error handled by driver or Oracle error number and message for database error
				}
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
			System.out.println(count);
		
		return count;
	}
	

}
