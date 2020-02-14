package commandline;
/**
 * 
 * @author Mike
 * matric 2205885M
 * <br><br>
 * constructor: <br>
 * String name, PlayerHand gameHand <br><br>
 * int getHighestAttribute() <br>
 *
 */

public class ComputerPlayer extends Player {
	
	private Card currentCard;

	public ComputerPlayer(String name, PlayerHand gameHand) {
		super(name, gameHand);
		
	}
	/**
	 * Create tempArray to store a Player's currentCard values 
	 * store initial value in maxValue and compare with all other values.
	 * if the value is greater than initial it is stored and its index stored in "index"
	 * 
	 * @return index of the largest value in values array returned to be used as 
	 * chosen attribute
	 */
	
	public int getHighestAttribute() 
	{ 
		int[] tempArray = new int[5];
		tempArray= currentCard.getValues();
		int maxValue = currentCard.getValues()[0];
		int index = 0;
		for (int i = 0; i < currentCard.getValues().length; i++) {
			tempArray[i] = currentCard.getValues()[i];
		if (currentCard.getValues()[i] > maxValue) {
			maxValue = currentCard.getValues()[i];
			index = i;
			
		}
		}return index +1;
	}

}
