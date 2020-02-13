package commandline;

/**
 * 
 * @author Aaron Callaghan
 * <br><br>
 *
 *Constructore:<br>
 *	PlayerStats(Player p, int s)<br><br>
 *
 *Public methods:<br>
 *	public String getPlayer()<br>
 *	public int getScore()
 */
public class PlayerStats {

	// Stores Player
	Player player;
	// Stores score
	int score;
	
	/**
	 * Constructor
	 * @param Player
	 * @param int
	 */
	public PlayerStats(Player p, int s) {
		
		player = p;
		score = s;
	}
	
	/**
	 * Returns player name
	 * @return String
	 */
	public String getPlayer() {
		
		return player.getName();
	}
	
	/**
	 * Returns player score
	 * @return int
	 */
	public int getScore() {
		
		return score;
	}
}
