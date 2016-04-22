Feature: Landing page
	The user makes a get petition	
	Scenario: Enter Application
	  Given three votings available, one with description: 'Voting 1'; 
	  And another one with description 'Voting 2'
	  And another one with description 'Voting 3'
	When the user access the main page of the application
	Then the client receives status code of 200
	  And a list of 3 votings must be shown
	  And the client receives the string "Voting 1"
	  And the client receives the string "Voting 2"
	  And the client receives the string "Voting 3"