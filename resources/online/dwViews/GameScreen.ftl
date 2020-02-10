<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">
    		
    		<!-- button to move to next round -->
    		<button onclick="startRound()" id="nextRound" type="button" class="btn btn-primary">Next Round</button>

    		<!-- button to move to results when AI selectign category -->
    		<button onclick="AIPlay()" id="next" type="button" class="btn btn-primary">Next</button>

    		<button onclick="startRoundAISolo()" id="AIOnly" type="button" class="btn btn-primary">Next (AI Only)</button>
    		
    		<!-- buttons to select category and move to result when humna selecting category -->
    		<div class="categories" role="group" aria-label="First group">
				<button onclick="humanPlay(1)" type="button" class="btn btn-secondary">1</button>
				<button onclick="humanPlay(2)" type="button" class="btn btn-secondary">2</button>
				<button onclick="humanPlay(3)" type="button" class="btn btn-secondary">3</button>
				<button onclick="humanPlay(4)" type="button" class="btn btn-secondary">4</button>
				<button onclick="humanPlay(5)" type="button" class="btn btn-secondary">5</button>
			</div>

			<!-- element to display who's turn it is-->
			<div id="turn"></div>

			<!-- element to display selected category -->
			<div id="selectedCat"></div>

			<!-- element to display any players eliminated in a round -->
			<div id="eliminatedPlayers"></div>

			<!-- element to display round result -->
			<div class="WinDraw"></div>

			<!-- element to display game winner -->
			<div id="gameWinner"></div>

			<!-- list to hold cards -->
			<ul class="list" style="display: inline; list-style-type: none; padding:5px; margin: 2px"> </ul>

		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
		
				// hide buttons to start
				$(".categories").hide();
				$("#nextRound").hide();
				$("#next").hide();
				$("#AIOnly").hide();

				// check whether player or AI turn and start round
				checkTurn();
				
			}
			
		
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
			
			/*
			 *	Functions to start game and display player card
			 */

			// function to check if a player has won
			function startRound() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showPlayers");

				// empty list of cards
				$(".list").empty();

				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var responseText = xhr.response;

					// convert to JSON object
					var jsonPlayers = JSON.parse(responseText);

					// for testing
					console.log("Players length: " + jsonPlayers.length);

					// if only 1 player left, game over and announce winner
					if(jsonPlayers.length == 1){
						$("#gameWinner").append("<h2>" + jsonPlayers[0].name + " wins the game!</h2>");
					}

					// else check whose turn it is and start game
					else{
						checkTurn();
					}

				}
				xhr.send();
			}

			// function to check if a player has won, used only after player is eliminated 
			function startRoundAISolo() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showPlayers");

				// empty list of cards
				$(".list").empty();
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var responseText = xhr.response;

					// convert to JSON object
					var jsonPlayers = JSON.parse(responseText);

					// for testing
					console.log("Players length: " + jsonPlayers.length);

					// if only 1 player left, game over and announce winner
					if(jsonPlayers.length == 1){
						$("#gameWinner").append("<h2>" + jsonPlayers[0].name + " wins the game!</h2>");
					}
					// else start new AI-only round
					else{
						AISoloPlay();
					}

				}
				xhr.send();
			}

			// function to check if it is human player's turn and start round
			function checkTurn(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/checkTurn");

				// empty text fields, cards and hide next round button
				$(".WinDraw").empty();
				$("#selectedCat").empty();
				$(".list").empty();
				$("#eliminatedPlayers").empty();
				$("#nextRound").hide();	

				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {

					// API returns 1 to indicated human turn, 2 to indicate AI turn
					var playerTurn = xhr.response;
					
					// draw cards for all players
					drawCards();

					// show player info for human players (including card)
					showPlayer();

					// if it is the human player's turn
					if(playerTurn == "1"){
						// show buttons to select category
						$(".categories").show();

						// show text to indicate player turn
						$("#turn").append("Your turn");
					}
					// else it is an AI turn
					else{
						// show 'next' button
						$("#next").show();

						// show text to indicate AI turn
						$("#turn").append("AI turn");
					}
					
				}
				xhr.send();
			}

			// function to draw new cards
			function drawCards() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/drawCards");

				// empty list of cards
				$(".list").empty();
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					// no logic, only API call to update model on server
				}
				xhr.send();
			}

			// function to show human player details
			function showPlayer(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showPlayer");

				// empty list of cards
				$(".list").empty();

				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					// get details of human player and convert to JSON
					var responseText = xhr.response;
					var jsonHumanPlayer = JSON.parse(responseText);

					// player name
					var playerNo = jsonHumanPlayer.name

					// card title
					var title = jsonHumanPlayer.card.desc

					// attribute values
					var size = jsonHumanPlayer.card.values[0]
					var speed = jsonHumanPlayer.card.values[1]
					var range = jsonHumanPlayer.card.values[2]
					var firepower = jsonHumanPlayer.card.values[3]
					var cargo = jsonHumanPlayer.card.values[4]

					// cards left in hand
					var cardsLeft = jsonHumanPlayer.handSize

					// append card details to list of cards
					$(".list").append(printCard(playerNo, title, size, speed, range, firepower, cargo, cardsLeft));
				}
				xhr.send();
			}

			// function to generate html to display card with image
			function printCard(playerNo, desc, size, speed, range, firepower, cargo, cardsLeft) {
				return "<li class='trumpCard' style='float: left'><div class='card' style='width: 18rem;'><img src='http://dcs.gla.ac.uk/~richardm/TopTrumps/"+desc+".jpg' class='card-img-top' alt='...'><div class='card-body'><h5 class='card-title'>" + playerNo + "</h5><h5 class='card-title'>" + desc + "</h5><br /><p class='size'>Size:	" + size + "</p><p class='speed'>Speed:		"+speed+"</p><p class='range'>Range:	 " + range +"</p><p class='firepower'>Firepower: 	"+firepower+"</p><p class='cargo'>Cargo: 	"+cargo+"</p><br /><h6>Cards Left: " + cardsLeft + "</div></div></li>"
			}


			/*
			 *	Functions to organize API calls/game logic
			 *	
			 *	To be refactored, think this might be causing 
			 *	issues with synchronization
			 */

			// function to update model for human player round
			function humanPlay(category){

				// hide category buttons and empty text fields
				$(".categories").hide();
				$("#turn").empty();

				// update model for selected category
				selectCategory(category);

				// show cards of all players
				showPlayers();	

				// check whether win or draw. Identify winner if win
				winDraw();

				// check for and display any eliminations
				checkEliminations();
			}	

			// function to update model for AI player round
			function AIPlay(){

				// hide next button, empty fields
				$("#next").hide();
				$("#turn").empty();

				// instruct model to select category for active AI player
				AISelectCategory();

				// show cards of all players
				showPlayers();

				// check whether win or draw. Identify winner if win	
				winDraw();

				// check for and display any eliminations
				checkEliminations();
			}

			// function to update model for AI player round, used only after player is eliminated 
			function AISoloPlay(){

				// hide next button, empty fields
				$("#next").hide();
				$("#turn").empty();

				/* 	Additional functions, previously handled
					by checkTurn when human player active 
				*/
				$("#selectedCat").empty();
				$("#eliminatedPlayers").empty();
				drawCards();

				// instruct model to select category for active AI player
				AISelectCategory();

				// show cards of all players
				showPlayers();	

				// check whether win or draw. Identify winner if win
				winDraw();

				// check for and display any eliminations
				checkEliminationsAIOnly();

			}

			
			/*
			 *	API calls for logic above
			 */

			 // function to update model to select the provided category 
			function selectCategory(category){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/selectCategory?Category="+category);

				// empty list of cards
				$(".list").empty();
				
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {

					// for testing
					console.log("Player selected category: " + xhr.response);

					// display selected category
					$("#selectedCat").append("Selected category: " + xhr.response);
				}
				xhr.send();
			}

			// API call to play AI turn
			function AISelectCategory(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/AISelectCategory");

				// empty list of cards
				$(".list").empty();
				
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {

					// for testing
					console.log("AI selected category: " + xhr.response);

					// display selected category
					$("#selectedCat").append("Selected category: " + xhr.response);

				}
				xhr.send();
			}

			// function to show all players' details
			function showPlayers() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showPlayers");

				// empty list of cards
				$(".list").empty();
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {

					// get details of all players and convert to JSON
					var responseText = xhr.response;
					var jsonPlayers = JSON.parse(responseText);

					// loop through all players
					for(var i = 0; i < jsonPlayers.length; i++){

						// player name
						var playerNo = jsonPlayers[i].name

						// card title
						var title = jsonPlayers[i].card.desc

						// attribute values
						var size = jsonPlayers[i].card.values[0]
						var speed = jsonPlayers[i].card.values[1]
						var range = jsonPlayers[i].card.values[2]
						var firepower = jsonPlayers[i].card.values[3]
						var cargo = jsonPlayers[i].card.values[4]

						// cards left in hand
						var cardsLeft = jsonPlayers[i].handSize

						// append card details to list of cards
						$(".list").append(printCard(playerNo, title, size, speed, range, firepower, cargo, cardsLeft));
					}

				}
				xhr.send();
			}

			

			// function to check if win or draw
			function winDraw(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getResult");

				// empty text fields
				$(".WinDraw").empty();
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {

					// response of 1 indicated a player won, 2 indicates a draw
					var result = xhr.response;

					// for testing
					console.log("Win/draw int: " + result);

					// if a player has won, displayer winner
					if(result == "1"){
						$(".WinDraw").append("Winner: ");
						// if a player has won, the active player will have been updated to the winner
						getActivePlayer();
					}
					// otherwise indicate draw
					else{
						$(".WinDraw").append("Draw");
					}
					
				}
				xhr.send();
			}

			// function to get active player to display as winner
			function getActivePlayer(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getActivePlayer");

				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var result = xhr.response;
					$(".WinDraw").append("" + result);
					
					
				}
				xhr.send();
			}



			


			// function to show which (if any) players have been eliminated in a round
			function checkEliminations() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/checkEliminations");
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					// get list of any eliminated players and convert to JSON
					var responseText = xhr.response;
					var jsonPlayers = JSON.parse(responseText);

					// variable to check if human player has been eliminated in this round
					var humanEliminated = false;

					// if response is not "0", at least one player has been eliminated
					if(responseText != "0"){
						$("#eliminatedPlayers").append("Eliminated Players: <br />");

						// loop through list of eliminated players
						for(var i = 0; i < jsonPlayers.length; i++){

							// indicate if a player is player1
							if(jsonPlayers[i].name == "Player1"){
								humanEliminated = true;
							}
							
							// list eliminated players
							$("#eliminatedPlayers").append(jsonPlayers[i].name);

						}
					}

					// if human player eliminated, switch to AI-only logic
					if(humanEliminated){
						// hide next round button and show AIOnly button
						$("#nextRound").hide();
						$("#AIOnly").show();
					}
					// otherwise, continue with normal logic
					else{
						$("#nextRound").show();
					}

					checkHumanPlayer();

				}
				xhr.send();
			}

			/* 	function to show which (if any) players have been eliminated in a round
			 *	logic for when human player has been eliminated
			 */
			function checkEliminationsAIOnly() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/checkEliminations");
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {

					// get list of any eliminated players and convert to JSON
					var responseText = xhr.response;
					var jsonPlayers = JSON.parse(responseText);

					// if response is not "0", at least one player has been eliminated
					if(responseText != "0"){
						$("#eliminatedPlayers").append("Eliminated Players: <br />");

						// loop through list of eliminated players
						for(var i = 0; i < jsonPlayers.length; i++){
							
							// list eliminated players
							$("#eliminatedPlayers").append(jsonPlayers[i].name);

						}
					}
					// show AIOnly button
					$("#AIOnly").show();
					checkHumanPlayer();
				}
				xhr.send();
			}

			/*
			 *	Check if human player still active
			 */

			 function checkHumanPlayer(){
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/checkHumanPlayer");
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {

					// get list of any eliminated players and convert to JSON
					var responseText = xhr.response;
			
					// show AIOnly button
					console.log("Check player API response: " + responseText);
				}
				xhr.send();
			 }


			

		</script>
		
		</body>
</html>