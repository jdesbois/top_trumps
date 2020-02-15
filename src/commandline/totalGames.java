package commandline;
import java.sql.*;

public class totalGames {
	
	private final String url = "jdbc:postgresql://localhost:5432/TopTrumps4"; 
	private final String username = "postgres"; 
	private final String password = "bex182";
	
	public Connection connect() { 
		
		Connection conn = null; 
		
		try {
			conn = DriverManager.getConnection(url, username, password); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;	
	}
	
	public int overallGameCount() {
		
		String SQL = "SELECT COUNT (id) FROM games";
		
		int count = 0;
		
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL)) {
				rs.next();
				count = rs.getInt(1);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return count;
	}
	
	public static void main(String [] args) {
		
		totalGames gm = new totalGames();
		gm.connect();
		
		int total = gm.overallGameCount();
		
		System.out.println(String.format("%d : total games played", total));		
		}

}
