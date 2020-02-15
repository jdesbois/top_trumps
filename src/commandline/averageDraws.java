package commandline;
import java.sql.*;

public class averageDraws {
	
	private final String url = "jdbc:postgresql://52.24.215.108:5432/MakeTrumpsGreatAgain";
	private final String username = "MakeTrumpsGreatAgain";
	private final String password = "MakeTrumpsGreatAgain";
	
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(url, username, password);	
	}
	
	public int drawsAverage() {
		
		String SQL = "SELECT AVG (no_draws) FROM games";
		
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
		
		averageDraws ad = new averageDraws();
		int average = ad.drawsAverage();
		
		System.out.println(String.format("%d : average number of draws", average));
		}

}
