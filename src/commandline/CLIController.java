package commandline;

/**
 * 
 * @author Aaron Callaghan
 * <br><br>
 * 
 * Constructor:<br>
 * 	CLIController(GameState model, CLIView view)<br><br>
 * 
 * Public methods:<br>
 * 	public int humanRound()<br>
 *	public in AIRound()
 */
public class CLIController {
	
	// Stores GameState(Model)
	GameState model;
	// Stores View
	CLIView view;
	
	/**
	 * Constructor
	 * @param GameState model
	 * @param CLIView view
	 */
	public CLIController(GameState model, CLIView view) {
		
		this.model = model;
		this.view = view;
	}
	
	/**
	 * Handles logic for human player round
	 */
	public int humanRound() {
		
		// Display player's drawn card
		view.displayHumanPlayerCard();
		
		// Requests and stores user input
		int choice = view.selectCategory();
		
		// Update model with chosen attribute
		model.setCurrentAttribute(choice - 1);
		
		// Returns round result
		return model.getResult();
	}
	
	/**
	 * Handles logic for AI round
	 */
	public int AIRound() {
		
		// Display player's drawn card
		view.displayHumanPlayerCard();
				
		// Requests and stores user input
		int choice = model.getActivePlayer().getHighestAttribute();
		
		// Print out choice
		System.out.println("Selected category: " + choice + "\n");
		
				
		// Update model with chosen attribute
		model.setCurrentAttribute(choice - 1);
		
		// Returns round result
		return model.getResult();
	}
}
