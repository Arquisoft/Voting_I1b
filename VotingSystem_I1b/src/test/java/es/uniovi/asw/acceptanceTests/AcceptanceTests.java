package es.uniovi.asw.acceptanceTests;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.Application;
import es.uniovi.asw.DBUpdate.DatabaseTestHelper;
import es.uniovi.asw.DBUpdate.JdbcHelper;
import es.uniovi.asw.DBUpdate.modelo.Vote;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.business.CheckVotingPhase;
import es.uniovi.asw.votingAccess.console.actions.MarkVoterAction;
import es.uniovi.asw.votingAccess.console.actions.SubmitVoteAction;

public class AcceptanceTests {
	
	
	private Exception exceptionThrown;
	private Object aux;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	String output;
	
	static WebDriver driver = new FirefoxDriver();
	
	static {
		try {
			Application.main(new String[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void loadUsers() throws SQLException {
		Voter voter1 = new Voter("78536214O", "User1", "user1@uniovi.es", 1, "user1");
		voter1.setEVoter(true);
		Voter voter2 = new Voter("78963254J", "User2", "user2@uniovi.es", 1, "user2");
		voter2.setHasVoted(false);
		voter2.setEVoter(false);
		Voter voter3 = new Voter("63321547G", "User3", "user3@uniovi.es", 1, "user3");
		voter3.setHasVoted(true);
		voter3.setEVoter(true);
		Voter voter4 = new Voter("85201465K", "User4", "user4@uniovi.es", 1, "user4");
		voter4.setEVoter(true);
		voter4.setHasVoted(true);
		Voter voter5 = new Voter("08561274R", "User5", "user5@uniovi.es", 1, "user5");
		voter5.setEVoter(true);
		voter5.setHasVoted(false);
		DatabaseTestHelper.insertVoter(voter1);
		DatabaseTestHelper.insertVoter(voter2);
		DatabaseTestHelper.insertVoter(voter3);
		DatabaseTestHelper.insertVoter(voter4);
		DatabaseTestHelper.insertVoter(voter5);
	}
	
	
	private void insertExampleVoters() throws SQLException {
		  DatabaseTestHelper.insertVoter(
				  new Voter("81380579U", "Perico",
						  "perico@uniovi.es", 1, "soyPerico", false, true));
		  DatabaseTestHelper.insertVoter(new Voter("55824978L", "Alberto",
				  "alberto@uniovi.es", 2, "albertoPassword", false, false));
		  DatabaseTestHelper.insertVoter(new Voter("58584762G", "Pepe",
				  "pepe@uniovi.es", 2, "passwordPEPE", false, false));
	}
	
	private void deleteUsers() throws SQLException {
		DatabaseTestHelper.deleteVoters();
	}
	
	
	
	
	@Given("^There is not any voter in the census with NIF \"([^\"]*)\"$")
	public void there_is_not_any_voter_in_the_census_with_NIF(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    org.junit.Assert.assertNull(DatabaseTestHelper.findVoter(arg1));
	}
	
	@Given("^The voting has not started yet$")
	public void the_voting_has_not_started_yet() throws Throwable {
	    org.junit.Assert.assertTrue(new CheckVotingPhase().checkVotingPhase() == CheckVotingPhase.PRE_VOTING);
	}

	@When("^the voter introduces it's non-existing NIF \"([^\"]*)\"$")
	public void the_voter_introduces_it_s_non_existing_NIF(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.get("localhost:8080/");
	    WebElement element = driver.findElement(By.id("inputNIF"));
	    element.sendKeys(arg1);
	    element.submit();
	}

	@Then("^the user receives a message telling the NIF introduced is not in census$")
	public void the_user_receives_a_message_telling_the_NIF_introduced_is_not_in_census() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertTrue(driver.getPageSource().contains("NIF introduced is not in the census"));
		deleteUsers();
	}
	
	
	
	@Given("^There exist a user with NIF \"([^\"]*)\"$")
	public void there_exist_a_user_with_NIF(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    loadUsers();
	    org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter(arg1));
	}

	@Given("^The user with NIF \"([^\"]*)\" is already registered as e-voter$")
	public void the_user_with_NIF_is_already_registered_as_e_voter(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    org.junit.Assert.assertTrue(DatabaseTestHelper.findVoter(arg1).isEVoter());
	}

	@Given("^the voting as not yet started$")
	public void the_voting_as_not_yet_started() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    CheckVotingPhase.votingPhase = CheckVotingPhase.PRE_VOTING;
	}

	@When("^the voter introduces the NIF \"([^\"]*)\"$")
	public void the_voter_introduces_the_NIF(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("localhost:8080/");
	    WebElement element = driver.findElement(By.id("inputNIF"));
	    element.sendKeys(arg1);
	    element.submit();
	}

	@Then("^the user receives a message telling that he is already registered as e-voter$")
	public void the_user_receives_a_message_telling_that_he_is_already_registered_as_e_voter() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertTrue(driver.getPageSource().contains("NIF introduced is already an e-voter"));
		deleteUsers();
	}

	
	
	@Given("^there exist a user that has NIF \"([^\"]*)\"$")
	public void there_exist_a_user_that_has_NIF(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    loadUsers();
	    org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter(arg1));
	}

	@Given("^the user with NIF \"([^\"]*)\" is not registered as e-voter$")
	public void the_user_with_NIF_is_not_registered_as_e_voter(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertFalse(DatabaseTestHelper.findVoter(arg1).isEVoter());
	}

	@Given("^the voting day has not arrived yet$")
	public void the_voting_day_has_not_arrived_yet() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    CheckVotingPhase.votingPhase = CheckVotingPhase.PRE_VOTING;
	}
	
	@When("^the user introduces the NIF \"([^\"]*)\"$")
	public void the_user_introduces_the_NIF(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("localhost:8080/");
	    WebElement element = driver.findElement(By.id("inputNIF"));
	    element.sendKeys(arg1);
	    element.submit();
	}

	@Then("^the voter is registered for online voting$")
	public void the_voter_is_registered_for_online_voting() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    org.junit.Assert.assertTrue(DatabaseTestHelper.findVoter("78963254J").isEVoter());
	}

	@Then("^the user receives a message telling that he has been successfully registered as e-voter$")
	public void the_user_receives_a_message_telling_that_he_has_been_successfully_registered_as_e_voter() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertTrue(driver.getPageSource().contains("NIF successfully registered as e-voter"));
		deleteUsers();
	}
	
	
	
	@Given("^there is not any voter with \"([^\"]*)\" in the census$")
	public void there_is_not_any_voter_with_in_the_census(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		loadUsers();
	    org.junit.Assert.assertNull(DatabaseTestHelper.findVoter(arg1));
	}

	@Given("^It is voting time$")
	public void it_is_voting_time() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CheckVotingPhase.votingPhase = CheckVotingPhase.VOTING;
	}

	@When("^the voter introduces the NIF \"([^\"]*)\" and a wrong password$")
	public void the_voter_introduces_the_NIF_and_a_wrong_password(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("localhost:8080/");
	    WebElement element = driver.findElement(By.id("inputNIF"));
	    element.sendKeys(arg1);
	    element = driver.findElement(By.id("inputPassword"));
	    element.sendKeys("password");
	    element.submit();
	}

	@Then("^the application shows a message telling the user he is not in the census$")
	public void the_application_shows_a_message_telling_the_user_he_is_not_in_the_census() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertTrue(driver.getPageSource().contains(
				"given NIF does not correspond to any voter registered in the census"));
		deleteUsers();
	}
	
	
	@Given("^there exist a user with NIF \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void there_exist_a_user_with_NIF_and_password(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    loadUsers();
	    org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter(arg1));
	    org.junit.Assert.assertEquals(arg2, DatabaseTestHelper.findVoter(arg1).getPassword());
	}

	@Given("^we are in the middle of the voting time$")
	public void we_are_in_the_middle_of_the_voting_time() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    CheckVotingPhase.votingPhase = CheckVotingPhase.VOTING;
	}

	@When("^the voter introduces the NIF \"([^\"]*)\" and a wrong password \"([^\"]*)\"$")
	public void the_voter_introduces_the_NIF_and_a_wrong_password(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("localhost:8080/");
	    WebElement element = driver.findElement(By.id("inputNIF"));
	    element.sendKeys(arg1);
	    element = driver.findElement(By.id("inputPassword"));
	    element.sendKeys(arg2);
	    element.submit();
	}

	@Then("^the application tells the user that the password introduced is wrong$")
	public void the_application_tells_the_user_that_the_password_introduced_is_wrong() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertTrue(driver.getPageSource().contains("Wrong password"));
		deleteUsers();
	}
	
	
	
	
	
	@Given("^there exist a user in the census with NIF \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void there_exist_a_user_in_the_census_with_NIF_and_password(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		loadUsers();
	    org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter(arg1));
	    org.junit.Assert.assertEquals(arg2, DatabaseTestHelper.findVoter(arg1).getPassword());
	}

	@Given("^that same user with NIF \"([^\"]*)\" is not registered to vote electronically$")
	public void that_same_user_with_NIF_is_not_registered_to_vote_electronically(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    org.junit.Assert.assertFalse(DatabaseTestHelper.findVoter(arg1).isEVoter());
	}

	@Given("^we are in the time of the voting$")
	public void we_are_in_the_time_of_the_voting() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CheckVotingPhase.votingPhase = CheckVotingPhase.VOTING;
	}

	@When("^the voter inputs the NIF \"([^\"]*)\" and the password \"([^\"]*)\"$")
	public void the_voter_inputs_the_NIF_and_the_password(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("localhost:8080/");
	    WebElement element = driver.findElement(By.id("inputNIF"));
	    element.sendKeys(arg1);
	    element = driver.findElement(By.id("inputPassword"));
	    element.sendKeys(arg2);
	    element.submit();
	}

	@Then("^the program shows to the user a message telling he is not registered as e-voter$")
	public void the_program_shows_to_the_user_a_message_telling_he_is_not_registered_as_e_voter() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertTrue(driver.getPageSource().contains("user is not registered as e-voter"));
		deleteUsers();
	}
	
	
	
	
	
	@Given("^there is a user in the database with NIF \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void there_is_a_user_in_the_database_with_NIF_and_password(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    loadUsers();
		org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter(arg1));
	    org.junit.Assert.assertEquals(arg2, DatabaseTestHelper.findVoter(arg1).getPassword());
	}

	@Given("^that user with NIF \"([^\"]*)\" has already voted$")
	public void that_user_with_NIF_has_already_voted(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertTrue(DatabaseTestHelper.findVoter(arg1).getHasVoted());
	}

	@Given("^we are in the middle of the voting day$")
	public void we_are_in_the_middle_of_the_voting_day() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CheckVotingPhase.votingPhase = CheckVotingPhase.VOTING;
	}

	@When("^the user introduces in the application the NIF \"([^\"]*)\" and the password \"([^\"]*)\"$")
	public void the_user_introduces_in_the_application_the_NIF_and_the_password(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("localhost:8080/");
	    WebElement element = driver.findElement(By.id("inputNIF"));
	    element.sendKeys(arg1);
	    element = driver.findElement(By.id("inputPassword"));
	    element.sendKeys(arg2);
	    element.submit();
	}

	@Then("^the application shows the user a message telling he has already voted$")
	public void the_application_shows_the_user_a_message_telling_he_has_already_voted() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertTrue(driver.getPageSource().contains("The user has voted already"));
		deleteUsers();
	}
	
	
	
	
	
	@Given("^there exist a user stored in the system with NIF \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void there_exist_a_user_stored_in_the_system_with_NIF_and_password(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		loadUsers();
		org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter(arg1));
	    org.junit.Assert.assertEquals(arg2, DatabaseTestHelper.findVoter(arg1).getPassword());
	}

	@Given("^the user with NIF \"([^\"]*)\" has not voted yet$")
	public void the_user_with_NIF_has_not_voted_yet(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    org.junit.Assert.assertFalse((DatabaseTestHelper.findVoter(arg1).getHasVoted()));
	}

	@Given("^the that same user with NIF \"([^\"]*)\" is registered as e-voter$")
	public void the_that_same_user_with_NIF_is_registered_as_e_voter(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertTrue((DatabaseTestHelper.findVoter(arg1).isEVoter()));
	}

	@Given("^we voting day is on$")
	public void we_voting_day_is_on() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		CheckVotingPhase.votingPhase = CheckVotingPhase.VOTING;
	}
	
	@When("^the user introduces into the application the NIF \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void the_user_introduces_into_the_application_the_NIF_and_password(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("localhost:8080/");
	    WebElement element = driver.findElement(By.id("inputNIF"));
	    element.sendKeys(arg1);
	    element = driver.findElement(By.id("inputPassword"));
	    element.sendKeys(arg2);
	    element.submit();
	}

	@Then("^the system shows the user a checklist with the options in the election$")
	public void the_system_shows_the_user_a_checklist_with_the_options_in_the_election() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertTrue(driver.getPageSource().contains("Please select your vote"));
		org.junit.Assert.assertTrue(driver.getPageSource().contains("Yes"));
		org.junit.Assert.assertTrue(driver.getPageSource().contains("No"));
		deleteUsers();
	}
	
	
	
	

	@Given("^There is a user in the database that has NIF \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void there_is_a_user_in_the_database_that_has_NIF_and_password(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		loadUsers();
		org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter(arg1));
	    org.junit.Assert.assertEquals(arg2, DatabaseTestHelper.findVoter(arg1).getPassword());
	}
	
	@Given("^that user with \"([^\"]*)\" as NIF has not voted yet$")
	public void that_user_with_as_NIF_has_not_voted_yet(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertFalse((DatabaseTestHelper.findVoter(arg1).getHasVoted()));
	}

	@Given("^that user with NIF \"([^\"]*)\" is registered as e-voter$")
	public void that_user_with_NIF_is_registered_as_e_voter(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		org.junit.Assert.assertTrue((DatabaseTestHelper.findVoter(arg1).isEVoter()));
	}

	@Given("^the user has successfully submitted his NIF \"([^\"]*)\" and \"([^\"]*)\" password$")
	public void the_user_has_successfully_submitted_his_NIF_and_password(String arg1, String arg2) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		driver.get("localhost:8080/");
	    WebElement element = driver.findElement(By.id("inputNIF"));
	    element.sendKeys(arg1);
	    element = driver.findElement(By.id("inputPassword"));
	    element.sendKeys(arg2);
	    element.submit();
	}

	@When("^the user selects to vote for option \"([^\"]*)\" and submits the vote$")
	public void the_user_selects_to_vote_for_option_and_submits_the_vote(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		DatabaseTestHelper.deleteVotes();
	    WebElement element = driver.findElement(By.id("Yes"));
	    element.click();
	    element.submit();
	}

	@Then("^the option selected by the user is added as a new vote in the database$")
	public void the_option_selected_by_the_user_is_added_as_a_new_vote_in_the_database() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    org.junit.Assert.assertEquals(1,  DatabaseTestHelper.findVotes().size());
	    org.junit.Assert.assertEquals("yes", DatabaseTestHelper.findVotes().get(0).getOption());
	}

	@Then("^the user with NIF \"([^\"]*)\" is marked as already voted$")
	public void the_user_with_NIF_is_marked_as_already_voted(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    org.junit.Assert.assertTrue(DatabaseTestHelper.findVoter(arg1).getHasVoted());
	}

	@Then("^the application shows a message telling that the vote has been submitted successfully$")
	public void the_application_shows_a_message_telling_that_the_vote_has_been_submitted_successfully() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    org.junit.Assert.assertTrue(driver.getPageSource().contains("Thank you for submitting your vote"));
	}
	
	
	
	
	@Given("^the electoral board chooses the option of submit a vote$")
	  public void the_electoral_board_chooses_the_option_of_submit_a_vote() throws Throwable {
	  }

	  @When("^the electoral board selects a correct voting option$")
	  public void the_electoral_board_selects_a_correct_voting_option() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  DatabaseTestHelper.deleteVotes();
		  System.setIn(new ByteArrayInputStream("2\n".getBytes()));
		  new SubmitVoteAction().askUser(new BufferedReader(
				  new InputStreamReader(System.in)),
				  new PrintStream(outContent), System.err);
		  
	  }

	  @Then("^the vote selected by the board is added to the database$")
	  public void the_vote_selected_by_the_board_is_added_to_the_database() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      List<Vote> votes = DatabaseTestHelper.findVotes();
	      org.junit.Assert.assertEquals(1, votes.size());
	      org.junit.Assert.assertEquals("N", votes.get(0).getOption());
	      DatabaseTestHelper.deleteVotes();
	  }
	  
	  
	  @Given("^there exist a vote with NIF (\\d+)L, who has not already voted and is not registered as e voter$")
	  public void there_exist_a_vote_with_NIF_L_who_has_not_already_voted_and_is_not_registered_as_e_voter(int arg1) throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  JdbcHelper.setConnectionConfig(DatabaseTestHelper.DB_CONFIG_FILE);
		  DatabaseTestHelper.deleteVoters();
		  insertExampleVoters();
		  org.junit.Assert.assertNotNull(DatabaseTestHelper.findVoter("55824978L"));
		  org.junit.Assert.assertFalse(
				  DatabaseTestHelper.findVoter("55824978L").getHasVoted());
		  org.junit.Assert.assertFalse(
				  DatabaseTestHelper.findVoter("55824978L").isEVoter());
	  }

	  @When("^the electoral board introduces the NIF to mark it as already voted$")
	  public void the_electoral_board_introduces_the_NIF_to_mark_it_as_already_voted() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
		  System.setIn(new ByteArrayInputStream("55824978L\n".getBytes()));
		  new MarkVoterAction(2).askUser(new BufferedReader(
				  new InputStreamReader(System.in)),
				  new PrintStream(outContent), System.err);
	  }
	  

	  @Then("^the voter with the NIF is marked as voted$")
	  public void the_voter_with_the_NIF_is_marked_as_voted() throws Throwable {
	      // Write code here that turns the phrase above into concrete actions
	      org.junit.Assert.assertTrue(
	    		  DatabaseTestHelper.findVoter("55824978L").getHasVoted());
	      DatabaseTestHelper.deleteVoters();
	  }
}
