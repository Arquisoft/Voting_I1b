Feature: Voting Results
	The user makes a get petition to /voting
	Scenario: Enter Application
	  Given a voting with description: 'Voting 1', id = 1 and  has 1 'Si' votes and 2 'No' votes
	When the user access /voting?id=1
	Then the results must be shown
	  And Voting 1 must 1 'Si' votes
	  And Voting 1 must 2 'No' votes