package commandline;


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
		
		// All Player Objects draw new Card
		model.drawNewCard();
		
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
		
		// All Player Objects draw new Card
		model.drawNewCard();
				
		// Requests and stores user input
		int choice = model.getActivePlayer().getHighestAttribute();
				
		// Update model with chosen attribute
		model.setCurrentAttribute(choice - 1);
		
		// Returns round result
		return model.getResult();
	}
}
