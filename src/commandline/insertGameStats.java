package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class insertGameStats {
	
	
	private final String url = "jdbc:postgresql://52.24.215.108:5432/MakeTrumpsGreatAgain";
	private final String username = "MakeTrumpsGreatAgain";
	private final String password = "MakeTrumpsGreatAgain";
	
	public Connection connect() throws SQLException {  
		return DriverManager.getConnection(url, username, password); 

	}
	
	public long insert(GameStats g1) { 						
		String SQL = "INSERT INTO games(no_rounds,no_draws,winner)" + "VALUES(?,?,?)"; 
		
		long count = 0;
		
		try (Connection conn = connect();
				PreparedStatement pstmt = conn.prepareStatement(SQL,
				Statement.RETURN_GENERATED_KEYS)) {
			
			pstmt.setInt(1, g1.getRounds());
			pstmt.setInt(2, g1.getDraws());
			pstmt.setString(3, g1.getWinner());
	
			
			int affectedRows = pstmt.executeUpdate();
			
			if(affectedRows>0) {
				
				try(ResultSet rs = pstmt.getGeneratedKeys()) {
					if(rs.next()) {
						count = rs.getLong(1);
					}
					
				}catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
			System.out.println(count);
		
		return count;
	}
	

}
