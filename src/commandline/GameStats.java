package commandline;

/**
 * 
 * @author Aaron Callaghan
 * <br><br>
 * 
 * Constructor:<br>
 * 	GameStats(Player w, int rN, int d)<br><br>
 * 
 * Public methods:<br>
 *	public String getWinner()<br>
 *	public int getDraws()<br>
 *	public in getRounds()
 */
public class GameStats {

	// Stores number of draws
	int draws;
	// Stores number of rounds
	int rounds;
	// Stores game winner
	Player winner;
	
	
	/**
	 * Constructor
	 * @param Player
	 * @param int
	 * @patam int
	 */
	public GameStats(Player w, int rN, int d) {
		
		winner = w;
		rounds = rN;
		draws = d;
	}
	
	/**
	 * Returns winner name
	 * @return String
	 */
	public String getWinner() {
		
		return winner.getName();
	}
	
	/**
	 * Returns number of draws
	 * @return int
	 */
	public int getDraws() {
		
		return draws;
	}
	
	/**
	 * Returns number of rounds
	 * @retun int
	 */
	public int getRounds() {
		
		return rounds;
	}
}
