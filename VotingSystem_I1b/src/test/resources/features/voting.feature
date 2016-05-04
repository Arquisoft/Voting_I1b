Feature: A voter logs in and votes electronically

	Scenario: The NIF introduced by the voter does not correspond to a NIF in the census
	Given there is not any voter with "02547885J" in the census
	And It is voting time
	When the voter introduces the NIF "02547885J" and a wrong password
	Then the application shows a message telling the user he is not in the census
	
	Scenario: The password introduced by the voter is wrong
	Given there exist a user with NIF "78536214O" and password "user1"
	And we are in the middle of the voting time
	When the voter introduces the NIF "78536214O" and a wrong password "User0"
	Then the application tells the user that the password introduced is wrong
	
	Scenario: The voter is not registered to vote electronically
	Given there exist a user in the census with NIF "78963254J" and password "user2"
	And that same user with NIF "78963254J" is not registered to vote electronically
	And we are in the time of the voting
	When the voter inputs the NIF "78963254J" and the password "user2"
	Then the program shows to the user a message telling he is not registered as e-voter
	
	Scenario: The voter has already voted
	Given there is a user in the database with NIF "85201465K" and password "user4"
	And that user with NIF "85201465K" has already voted
	And we are in the middle of the voting day
	When the user introduces in the application the NIF "85201465K" and the password "user4"
	Then the application shows the user a message telling he has already voted
	
	Scenario: The user successfully logs in to vote
	Given there exist a user stored in the system with NIF "08561274R" and password "user5"
	And the user with NIF "08561274R" has not voted yet
	And the that same user with NIF "08561274R" is registered as e-voter
	And we voting day is on
	When the user introduces into the application the NIF "08561274R" and password "user5"
	Then the system shows the user a checklist with the options in the election
	
	Scenario: The voter submits a vote
	Given There is a user in the database that has NIF "08561274R" and password "user5"
	And that user with "08561274R" as NIF has not voted yet
	And that user with NIF "08561274R" is registered as e-voter
	And the user has successfully submitted his NIF "08561274R" and "user5" password
	When the user selects to vote for option "yes" and submits the vote
	Then the option selected by the user is added as a new vote in the database
	And the user with NIF "08561274R" is marked as already voted
	And the application shows a message telling that the vote has been submitted successfully