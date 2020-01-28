package commandline;

public class TestCLIView {
	public static void main(String args[]) {
		// Test objects
		String[] testAttrib = {"height", "weight", "length", "ferocity", "intelligence"};
		int[] testVals = {10,10,10,10,10};
		Card testCard = new Card("Test Card", testAttrib, testVals);
		CLIView view = new CLIView();
		
		
		int playChoice = 0;
		
		/*
		 * Loop until new game selected (when value of 1 is returned from view)
		 */
		while(playChoice != 1) {
			playChoice = view.selectStats();
		}

		view.gameStarted();
		
		view.roundNumber();
		
		view.displayCard(testCard);
		
		view.selectCategory();
		
		view.displayResult(testCard);
		
		
		
	}
}
