Feature: A voter registers to vote electronically

	Scenario: The user introduces a NIF that is not in the census
	Given There is not any voter in the census with NIF "25697514K"
	And The voting has not started yet
	When the voter introduces it's non-existing NIF "25697514K"	
	Then the user receives a message telling the NIF introduced is not in census
	
	Scenario: The voter is already register for voting electronically
	Given There exist a user with NIF "78536214O"
	And The user with NIF "78536214O" is already registered as e-voter
	And the voting as not yet started
	When the voter introduces the NIF "78536214O"
	Then the user receives a message telling that he is already registered as e-voter
	
	Scenario: The voter exists and it is not registered for online voting
	Given there exist a user that has NIF "78963254J"
	And the user with NIF "78963254J" is not registered as e-voter
	And the voting day has not arrived yet
	When the user introduces the NIF "78963254J"
	Then the voter is registered for online voting
	And the user receives a message telling that he has been successfully registered as e-voter
	
    