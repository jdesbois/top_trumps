package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class insertPlayerStats {
	
	private final String url = "jdbc:postgresql://localhost:5432/TopTrumps4"; 
	private final String username = "postgres"; 
	private final String password = "bex182";
	
	public Connection connect() throws SQLException {  
		return DriverManager.getConnection(url, username, password); 

	}
	
	
	public void insertData(int gid, ArrayList <PlayerStats> list) {
		
		String SQL = "INSERT INTO player(game_id,player_id,rounds_won)" + "VALUES (?,?,?)";
		
		try ( Connection conn = connect();
			  PreparedStatement statement = conn.prepareStatement(SQL);) {
			
			int count = 0;	
			
			for (PlayerStats player : list) {
				statement.setInt(1, gid);
				statement.setString(2, player.getPlayer());
				System.out.println("scorevalue:" + player.getScore());
				statement.setInt(3, player.getScore());
				
				statement.addBatch();
				count++;
			}
		statement.executeBatch();
		
	}catch(SQLException e) {
		
		System.out.println(e.getMessage());
	}
	
}

}
