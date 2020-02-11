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

		<!-- additional styles -->
		 <style>
        .jumbotron {
            padding-top: 10px !important;
            padding-bottom: 10px !important;
            background: lightgray;
        }

        .winzies:hover {
            background: lightgray;
        }

        .card_Value_0:hover {
            background: lightgray;
        }

        .card_Value_1:hover {
            background: lightgray;
        }

        .card_Value_2:hover {
            background: lightgray;
        }

        .card_Value_3:hover {
            background: lightgray;
        }

        .card_Value_4:hover {
            background: lightgray;
        }

        .myCards {
            border: 2px solid black;
            background: lightgray;
        }

        .nextRound:hover {
            background: lightgray;
        }
        .body1 {
            border: 2px solid black;
        }
    </style>

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    	
    	<div class="container">
    		
    		<!-- main heading -->
    		<div class="jumbotron">
		        <h3 class="display-4">Top Trumps game </h3>
		        <p class="lead"></p>
		    </div>

		    <div class="container_atri_chosen">

		        <div class="row justify-content-centre row_1">
		            <div class="col col_1.1">
		                <h2 id="roundNo"><h2>
		                <h4 id="activePlayer"></h4>
		                <h4 id="communalPileSize"></h4>
		                <!-- <h2>playerName has selected atributeName </h2> -->
		            </div>
		        </div>


		        <!-- container for buttons and text descriptions -->
		        <div class="row justify-content-around row2">
		            <div class="col-2 col2_1">
		                <div class="row justify-content-around verticalSeparation">
		                    <div class="col-12 invisible"> " Vertical separation" </div>
		                </div>
		                <div class="card bg-secondary user_choice">
		                    <div class="card-body body1">
		                        

		                    	<!-- buttons -->
		                        <button onclick="startRound()" type="button" class="btn btn-light" id="nextRound">Next Round</button>
		                        <button onclick="AISelectCategory()" type="button" class="btn btn-light" id="next">Next</button>
		                        <button onclick="newGame()" type="button" class="btn btn-light" id="newGame">New Game</button>

		                        <div class="btn-group-vertical bg-secondary my_buttons" id="categories">
		                            <h5> Please select a category</h5>
		                            <button onclick="selectCategory(1)" type="button" class="btn btn-default card_Value_0"> Select Size </button>
		                            <button onclick="selectCategory(2)" type="button" class="btn btn-default card_Value_1"> Select Speed </button>
		                            <button onclick="selectCategory(3)" type="button" class="btn btn-default card_Value_2"> Select Range </button>
		                            <button onclick="selectCategory(4)" type="button" class="btn btn-default card_Value_3"> Select Firepower </button>
		                            <button onclick="selectCategory(5)" type="button" class="btn btn-default card_Value_4"> Select Cargo </button>
		                        </div>

		                        <!-- text description fields -->
		                        <h6 class="card-title get_activeP" id="turn"></h6>
		                        <p class="card-text atri_select" id="selectedCat"></p>
		                        <h6 id="winDraw"></h6>
		                        <h6 id="eliminatedPlayers"></h6>
		                        <h6 id="gameWinner"></h6>

		                    </div>
		                </div>
		            </div>

		            <!-- cards -->


		            <div class="col-8">

		                <div class="container card_Container">
		                    <div class="row justify-content-around playersRow1">
		                        <div class="row justify-content-around verticalSeparation">
		                            <div class="col-12 invisible"> " Vertical separation" </div>
		                        </div>
		                        <div class="row justify-content-around cardRow1">
		                            <div class="col-md-4 col-sm-6 Hplayer" id="card1">
		                                <div class="card myCards">
		                                    <div class="card-header getHp_Name" id="nameAndDeckSize1">
		                                        Hp name + deckSize
		                                    </div>
		                                    <img src="https://smileybooth.co.uk/wp-content/uploads/2016/04/Top-Trumps-Play-Discover-Logo-1-copy.jpg" alt="" class="img-fluid">
		                                    <div class="card-block">
		                                        <div class="card-title">
		                                            <div class="row justify-content-around Title_row">
		                                                <div class="col-6 title_col">
		                                                    <div class="Hp_cardName" id="cName1">
		                                                        san francisco
		                                                    </div>
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="card-text">
		                                            <div class="row justify-content-around card_row0">
		                                                <div class="col-6 card_col1">
		                                                    Size
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Hp_val0" id="size1">
		                                                        10
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row1">
		                                                <div class="col-6 card_col1">
		                                                    Speed
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Hp_val1" id="speed1">
		                                                        5
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row2">
		                                                <div class="col-6 card_col1">
		                                                    Range
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Hp_val2" id="range1">
		                                                        6
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row3">
		                                                <div class="col-6 card_col1">
		                                                    Firepower
		                                                </div>
		                                                <div class="col-4 card_col2" id="firepower1">
		                                                    <div class="Hp_val3">
		                                                        10
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row_4">
		                                                <div class="col-6 card_col1">
		                                                    Cargo
		                                                </div>
		                                                <div class="col-4 card_col2" id="cargo1">
		                                                    <div class="Hp_val4">
		                                                        1
		                                                    </div>
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>

		                            <div class="col-md-4 col-sm-6 Player_a" id="card2">
		                                <div class="card myCards">
		                                    <div class="card-header getPlayer_a" id="nameAndDeckSize2">
		                                        pName pdecksize
		                                    </div>
		                                    <img src="https://smileybooth.co.uk/wp-content/uploads/2016/04/Top-Trumps-Play-Discover-Logo-1-copy.jpg" alt="" class="img-fluid">
		                                    <div class="card-block">
		                                        <div class="card-title">
		                                            <div class="row justify-content-around Title_row">
		                                                <div class="col-6 title_col">
		                                                    <div class="Pa_cardName" id="cName2">
		                                                        san francisco
		                                                    </div>
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="card-text">
		                                            <div class="row justify-content-around card_row0">
		                                                <div class="col-6 card_col1">
		                                                    Size
		                                                </div>
		                                                <div class="col-4 card_col2" id="size2">
		                                                    <div class="Pa_val0">
		                                                        10
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row1">
		                                                <div class="col-6 card_col1">
		                                                    Speed
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Pa_val1" id="speed2">
		                                                        5
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row2">
		                                                <div class="col-6 card_col1">
		                                                    Range
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Pa_val2" id="range2">
		                                                        6
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row3">
		                                                <div class="col-6 card_col1">
		                                                    Firepower
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Pa_val3" id="firepower2">
		                                                        10
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row_4">
		                                                <div class="col-6 card_col1">
		                                                    Cargo
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Pa_val4" id="cargo2">
		                                                        1
		                                                    </div>
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>

		                            <div class="col-md-4 col-sm-6 Player_b" id="card3">
		                                <div class="card myCards">
		                                    <div class="card-header bPlayer" id="nameAndDeckSize3">
		                                        bName bdeck
		                                    </div>
		                                    <img src="https://smileybooth.co.uk/wp-content/uploads/2016/04/Top-Trumps-Play-Discover-Logo-1-copy.jpg" alt="" class="img-fluid">
		                                    <div class="card-block">
		                                        <div class="card-title">
		                                            <div class="row justify-content-around Title_row">
		                                                <div class="col-6 title_col">
		                                                    <div class="Pb_cardName" id="cName3">
		                                                        san francisco
		                                                    </div>
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="card-text">
		                                            <div class="row justify-content-around card_row0">
		                                                <div class="col-6 card_col1">
		                                                    Size
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Pb_val0" id="size3">
		                                                        10
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row1">
		                                                <div class="col-6 card_col1">
		                                                    Speed
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Pb_val1" id="speed3">
		                                                        5
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row2">
		                                                <div class="col-6 card_col1">
		                                                    Range
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Pb_val2" id="range3">
		                                                        6
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row3">
		                                                <div class="col-6 card_col1">
		                                                    Firepower
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Pb_val3" id="firepower3">
		                                                        10
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <div class="row justify-content-around card_row_4">
		                                                <div class="col-6 card_col1">
		                                                    Cargo
		                                                </div>
		                                                <div class="col-4 card_col2">
		                                                    <div class="Pb_val4" id="cargo3">
		                                                        1
		                                                    </div>
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                    </div>
		                    <div class="row justify-content-around verticalSeparation">
		                        <div class="col-12 invisible"> " Vertical separation" </div>
		                    </div>
		                    <div class="row justify-content-around secondRound">
		                        <div class="col-md-4 col-sm-6 Player_c" id="card4">
		                            <div class="card myCards">
		                                <div class="card-header cPlayer" id="nameAndDeckSize4">
		                                    cName cDeck
		                                </div>
		                                <img src="https://smileybooth.co.uk/wp-content/uploads/2016/04/Top-Trumps-Play-Discover-Logo-1-copy.jpg" alt="" class="img-fluid">
		                                <div class="card-block">
		                                    <div class="card-title">
		                                        <div class="row justify-content-around Title_row">
		                                            <div class="col-6 title_col">
		                                                <div class="Pc_cardName" id="cName4">
		                                                    san francisco
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div class="card-text">
		                                        <div class="row justify-content-around card_row0">
		                                            <div class="col-6 card_col1">
		                                                Size
		                                            </div>
		                                            <div class="col-4 card_col2">
		                                                <div class="Pc_val0" id="size4">
		                                                    10
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="row justify-content-around card_row1">
		                                            <div class="col-6 card_col1">
		                                                Speed
		                                            </div>
		                                            <div class="col-4 card_col2">
		                                                <div class="Pc_val1" id="speed4">
		                                                    5
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="row justify-content-around card_row2">
		                                            <div class="col-6 card_col1">
		                                                Range
		                                            </div>
		                                            <div class="col-4 card_col2">
		                                                <div class="Pc_val2" id="range4">
		                                                    6
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="row justify-content-around card_row3">
		                                            <div class="col-6 card_col1">
		                                                Firepower
		                                            </div>
		                                            <div class="col-4 card_col2">
		                                                <div class="Pc_val3" id="firepoer4">
		                                                    10
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="row justify-content-around card_row_4">
		                                            <div class="col-6 card_col1">
		                                                Cargo
		                                            </div>
		                                            <div class="col-4 card_col2">
		                                                <div class="Pc_val4" id="cargo4">
		                                                    1
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
		                        <div class="col-md-4 col-sm-6 Player_d" id="card5">
		                            <div class="card myCards">
		                                <div class="card-header dPlayer" id="nameAndDeckSize5">
		                                    dName dDeck
		                                </div>
		                                <img src="https://smileybooth.co.uk/wp-content/uploads/2016/04/Top-Trumps-Play-Discover-Logo-1-copy.jpg" alt="" class="img-fluid">
		                                <div class="card-block">
		                                    <div class="card-title">
		                                        <div class="row justify-content-around Title_row">
		                                            <div class="col-6 title_col">
		                                                <div class="Pd_cardName" id="cName5">
		                                                    san francisco
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
		                                    <div class="card-text">
		                                        <div class="row justify-content-around card_row0">
		                                            <div class="col-6 card_col1">
		                                                Size
		                                            </div>
		                                            <div class="col-4 card_col2">
		                                                <div class="Pd_val0" id="size5">
		                                                    10
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="row justify-content-around card_row1">
		                                            <div class="col-6 card_col1">
		                                                Speed
		                                            </div>
		                                            <div class="col-4 card_col2">
		                                                <div class="Pd_val1" id="speed5">
		                                                    5
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="row justify-content-around card_row2">
		                                            <div class="col-6 card_col1">
		                                                Range
		                                            </div>
		                                            <div class="col-4 card_col2">
		                                                <div class="Pd_val2" id="range5">
		                                                    6
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="row justify-content-around card_row3">
		                                            <div class="col-6 card_col1">
		                                                Firepower
		                                            </div>
		                                            <div class="col-4 card_col2">
		                                                <div class="Pd_val3" id="firepower5">
		                                                    10
		                                                </div>
		                                            </div>
		                                        </div>
		                                        <div class="row justify-content-around card_row_4">
		                                            <div class="col-6 card_col1">
		                                                Cargo
		                                            </div>
		                                            <div class="col-4 card_col2">
		                                                <div class="Pd_val4" id="cargo5">
		                                                    1
		                                                </div>
		                                            </div>
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
		                    </div>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>

		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
				
				// hide buttons and text
				$("#next").hide();
				$("#nextRound").hide();
				$("#newGame").hide();
				$("#categories").hide();
				$("#turn").hide();
				$("#selectedCat").hide();
				$("#winDraw").hide();
				$("#eliminatedPlayers").hide();
				$("#gameWinner").hide();

				// redundant?
				$("#AIOnly").hide();

				// start round
				newGame();
				
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

			 // function to initialize a new game
			 function newGame(){
			 	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/newGame");


				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					
					// hide buttons
					$("#newGame").hide();
					$("#gameWinner").empty();
					$("#gameWinner").hide();


					// check active player
					getActivePlayer();

				}
				xhr.send();
			 }

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
					// console.log("Players length: " + jsonPlayers.length);

					// if only 1 player left, game over and announce winner
					if(jsonPlayers.length == 1){

						// show text that player has won game
						$("#gameWinner").show();
						$("#gameWinner").append("<h2>" + jsonPlayers[0].name + " wins the game!</h2>");

						// Show new game instead of next round
						$("#newGame").show();
						$("#nextRound").hide();
					}

					// else check whose turn it is and start game
					else{
						getCommunalPileSize();
					}

				}
				xhr.send();
			}

			// get communal pile size
			function getCommunalPileSize(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/communalPileSize");

				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var result = xhr.response;

					$("#communalPileSize").text("Communal pile size: " + result);

					getActivePlayer();					
					
				}
				xhr.send();
			}

			// function to get active player
			function getActivePlayer(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getActivePlayer");

				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var result = xhr.response;

					console.log("/getActivePlayer response: " + result);
					$("#activePlayer").text("Active player: " + result);

					drawCards();					
					
				}
				xhr.send();
			}


			// function to draw new cards
			function drawCards() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/drawCards");

				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {

					// get new round number
					getRoundNumber();
				}
				xhr.send();
			}

			// function to draw new cards
			function getRoundNumber() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundNo");

				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var roundNo = xhr.response;

					$("#roundNo").text("Round number: " + roundNo);

					// check to see if human player still in game
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

					// 1 - human player in game, 2 - no human player in game
					var responseText = xhr.response;

					// if human player present
					if(responseText == "1"){

						// show human player info
						showPlayer();
					}
					// otherwise, AI game 
					else{
						$("#eliminatedPlayers").empty();
						$("#selectedCat").empty();
						AISelectCategory();
					}
			
					// for testing
					console.log("Check player API response: " + responseText);
				}
				xhr.send();
			 }

			 // function to show human player details
			function showPlayer(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showPlayer");

				// hide non-human cards
				$("#card2").hide();
				$("#card3").hide();
				$("#card4").hide();
				$("#card5").hide();

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

					// cards left in hand
					var cardsLeft = jsonHumanPlayer.handSize

					// player name and number of cards in deck
					$("#nameAndDeckSize1").text(playerNo + "	Cards:" + cardsLeft);

					// card name
					$("#cName1").text(title);

					// category values
					$("#size1").text(jsonHumanPlayer.card.values[0]);
					$("#speed1").text(jsonHumanPlayer.card.values[1]);
					$("#range1").text(jsonHumanPlayer.card.values[2]);
					$("#firepower1").text(jsonHumanPlayer.card.values[3]);
					$("#cargo1").text(jsonHumanPlayer.card.values[4]);

					// check if human or AI turn
					checkTurn();
				}
				xhr.send();
			}

			// function to check if it is human player's turn and start round
			function checkTurn(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/checkTurn");

				// hide text and buttons
				$("#selectedCat").hide();
				$("#roundWinner").hide();
				$("#next").hide();
				$("#categories").hide();
				$("#winDraw").hide();
				$("#nextRound").hide();
				
				$("#eliminatedPlayers").empty();
				$("#eliminatedPlayers").hide();


				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {

					// API returns 1 to indicated human turn, 2 to indicate AI turn
					var playerTurn = xhr.response;
					

					// if it is the human player's turn
					if(playerTurn == "1"){
						// show buttons to select category
						$("#categories").show();
					}
					// else it is an AI turn
					else{
						// show 'next' button
						$("#next").show();

						// show text to indicate AI turn
						$("#turn").text("AI turn");
					}
					
				}
				xhr.send();
			}


			 // function to update model to select the provided category 
			function selectCategory(category){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/selectCategory?Category="+category);

				// hide category buttons and turn field
				$("#categories").hide();
				$("#turn").hide();

				// show result fields
				$("#selectedCat").show();
				$("#eliminatedPlayers").show();
				$("#winDraw").show();
				$("#roundWinner").show();
				
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {

					// for testing
					console.log("Player selected category: " + xhr.response);

					// display selected category
					$("#selectedCat").text("Selected category: " + xhr.response);

					showPlayers();
				}
				xhr.send();
			}

			// API call to play AI turn
			function AISelectCategory(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/AISelectCategory");

				// hide next button
				$("#next").hide();

				// show results fields
				$("#selectedCat").show();
				$("#eliminatedPlayers").show();
				$("#winDraw").show();
				$("#roundWinner").show();
				
				
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {

					// for testing
					console.log("AI selected category: " + xhr.response);

					// display selected category
					$("#selectedCat").text("Selected category: " + xhr.response);
					showPlayers();

				}
				xhr.send();
			}

			// function to show all players' details
			function showPlayers() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showPlayers");

				// hide all card positions to start
				$("#card1").hide();
				$("#card2").hide();
				$("#card3").hide();
				$("#card4").hide();
				$("#card5").hide();

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

						// cards left in hand
						var cardsLeft = jsonPlayers[i].handSize

						// show card
						$("#card" + (i+1)).show();

						// player name and number of cards in deck
						$("#nameAndDeckSize" + (i+1)).text(playerNo + "	Cards:" + cardsLeft);
						console.log(playerNo + "	Cards:" + cardsLeft);

						// card name
						$("#cName" + (i+1)).text(title);

						// category values
						$("#size" + (i + 1)).text(jsonPlayers[i].card.values[0]);
						$("#speed" + (i + 1)).text(jsonPlayers[i].card.values[1]);
						$("#range" + (i + 1)).text(jsonPlayers[i].card.values[2]);
						$("#firepower" + (i + 1)).text(jsonPlayers[i].card.values[3]);
						$("#cargo" + (i + 1)).text(jsonPlayers[i].card.values[4]);

						
					}

					// check if round has been won or drawn
					winDraw();

				}
				xhr.send();
			}

			

			// function to check if win or draw
			function winDraw(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getResult");


				$("#WinDraw").empty();
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
						$("#winDraw").text("Winner: ");
						// if a player has won, the active player will have been updated to the winner
						getWinningPlayer();
					}
					// otherwise indicate draw
					else{
						$("#winDraw").text("Draw");
					}

					checkEliminations();
					
				}
				xhr.send();
			}

			// function to get active player to display as winner
			function getWinningPlayer(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getActivePlayer");

				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var result = xhr.response;
					$("#winDraw").append("" + result);
					
					
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

					// if response is not "0", at least one player has been eliminated
					if(responseText != "0"){
						$("#eliminatedPlayers").append("Eliminated Players: <br />");

						// loop through list of eliminated players
						for(var i = 0; i < jsonPlayers.length; i++){
							
							// list eliminated players
							$("#eliminatedPlayers").append(jsonPlayers[i].name);

						}
					}

					$("#nextRound").show();

				}
				xhr.send();
			}

	

		</script>
		
		</body>
</html>