package commandline;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** 
 * Inserts player data into Player table
 * @author Rebecca Dinneen
 *<br> <br>
 *
 *  */

public class insertPlayerStats {
	
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
	
	/** Method inserts game_id,player_id,rounds_won into Player table
	 * @param rs ResultSet object represents database result set returned after query executed
	 *@returns count largest integer in column 
	 *@throws java.sql.SQLException */
	
	
	public void insertData(int gid, ArrayList <PlayerStats> list) {   //game_id is foreign key from Games table, entered as id SERIAL using SQL
																	 //PlayerStats ArrayList passed to insertData method 
		//String stores SQL query
		String SQL = "INSERT INTO player(game_id,player_id,rounds_won)" + "VALUES (?,?,?)"; 
		
		try ( Connection conn = connect(); //Create Connection object
			  PreparedStatement statement = conn.prepareStatement(SQL);) { //PreparedStatement object created from Connection object, PreparedStatement object represents SQL query
			
			int count = 0;	
			
			for (PlayerStats player : list) {  //List of PlayerStats objects passed into loop for insertion into table
				statement.setInt(1, gid); ////setInt method called on PreparedStatement object, set to the game_id from Games table
				statement.setString(2, player.getPlayer()); //setInt method called on PreparedStatement object, getPlayer called to insert player_id 
				statement.setInt(3, player.getScore()); //setInt method called on PreparedStateement object, getScore called to get rounds_won 
				
				statement.addBatch(); //addBatch method of PreparedStatement called to add ArrayList of players to table
				count++;
			}
		statement.executeBatch(); //executeBatch method called 
		
	}catch(SQLException e) {
		
		System.out.println(e.getMessage());
	}
	
}

}
