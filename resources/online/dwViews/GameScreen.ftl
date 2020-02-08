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
    		<button onclick="showPlayerCard()" type="button" class="btn btn-primary">Player Card</button>
    		<button onclick="showAllCards()" type="button" class="btn btn-primary">All Cards</button>
    		<button onclick="drawShow()" type="button" class="btn btn-primary">Draw Cards</button>

    		<div class="btn-group mr-2" role="group" aria-label="First group">
				<button type="button" class="btn btn-secondary">1</button>
				<button type="button" class="btn btn-secondary">2</button>
				<button type="button" class="btn btn-secondary">3</button>
				<button type="button" class="btn btn-secondary">4</button>
				<button type="button" class="btn btn-secondary">5</button>
			</div>

			<!-- Add your HTML Here -->
			<ul class="list" style="display: inline; list-style-type: none; padding:5px; margin: 2px"> </ul>
		
		</div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				// helloJSONList();
				// helloWord("Student");

				// Draw cards
				drawCards();
				// Show current player's card
				showPlayerCard();
				// Show all players cards
				// showAllCards();
				// check whether player or AI turn
				checkTurn();
				
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
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
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

			// function to display card with image
			function printCard(desc, size, speed, range, firepower, cargo) {
				return "<li class='trumpCard' style='float: left'><div class='card' style='width: 18rem;'><img src='http://dcs.gla.ac.uk/~richardm/TopTrumps/"+desc+".jpg' class='card-img-top' alt='...'><div class='card-body'><h5 class='card-title'>" + desc + "</h5><p class='size'>Size:	" + size + "</p><p class='speed'>Speed:		"+speed+"</p><p class='range'>Range:	 " + range +"</p><p class='firepower'>Firepower: 	"+firepower+"</p><p class='cargo'>Cargo: 	"+cargo+"</p></div></div></li>"
			}

			// function to show players current card
			function showPlayerCard(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showPlayerCard");
				$(".list").empty();
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var responseText = xhr.response;
					var jsonActiveCard = JSON.parse(responseText);
					var title = jsonActiveCard.desc
					var size = jsonActiveCard.values[0]
					var speed = jsonActiveCard.values[1]
					var range = jsonActiveCard.values[2]
					var firepower = jsonActiveCard.values[3]
					var cargo = jsonActiveCard.values[4]
					$(".list").append(printCard(title, size, speed, range, firepower, cargo));
				}
				xhr.send();
			}

			// function to check if it is human player's turn
			function checkTurn(){
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/checkTurn");
				$(".list").empty();
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var playerTurn = xhr.response;
					if(playerTurn == "1"){
						$(".list").append("Your turn");
					}
					else{
						$(".list").append("Computer turn");
					}
					
				}
				xhr.send();
			}

			// function to show all players' cards
			function showAllCards() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/showAllCards");
				$(".list").empty();
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					var responseText = xhr.response;
					// console.log(responseText);
					var jsonCards = JSON.parse(responseText);
					// console.log(jsonCards);

					for(var i = 0; i < jsonCards.length; i++){
						var title = jsonCards[i].desc
						console.log(title)
						var size = jsonCards[i].values[0]
						var speed = jsonCards[i].values[1]
						var range = jsonCards[i].values[2]
						var firepower = jsonCards[i].values[3]
						var cargo = jsonCards[i].values[4]
						$(".list").append(printCard(title, size, speed, range, firepower, cargo));
					}

				}
				xhr.send();
			}

			// function to draw new cards
			function drawCards() {
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/drawCards");
				$(".list").empty();
				if(!xhr) {
					alert("CORS not supported");
				}
				xhr.onload = function(e) {
					

				}
				xhr.send();
			}

			// function to call drawCards() then showPlayerCard()
			function drawShow(){
				drawCards();
				showPlayerCard();

			}		

		</script>
		
		</body>
</html>