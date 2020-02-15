package commandline;
import java.sql.*;

public class computeWins {
	
	private final String url = "jdbc:postgresql://52.24.215.108:5432/MakeTrumpsGreatAgain";
	private final String username = "MakeTrumpsGreatAgain";
	private final String password = "MakeTrumpsGreatAgain";
	
	public Connection connect() { 
		
		Connection conn = null; 
		
		try {
			conn = DriverManager.getConnection(url, username, password); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;	
	}
	
	public int AIWins() {
		
		String SQL = "SELECT COUNT (winner) FROM games WHERE winner LIKE 'A%'";
		
		int count = 0;
		
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL)){
				rs.next();
				count = rs.getInt(1);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return count;
	}
	
	public int youWins() {
		
		String SQL = "SELECT COUNT (winner) FROM games WHERE winner LIKE 'Y%'";
		
		int count = 0;
		
		try (Connection conn = connect();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(SQL)){
				rs.next();
				count = rs.getInt(1);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return count;
	}
	
	
	public static void main(String [] args) {
		
		computeWins cw = new computeWins();

		
		int AIWins = cw.AIWins();
		int humanWins = cw.youWins();
		
		System.out.println(String.format("%d : AI wins", AIWins));
		System.out.println(String.format("%d : human wins", humanWins));
		
	}
			
}
