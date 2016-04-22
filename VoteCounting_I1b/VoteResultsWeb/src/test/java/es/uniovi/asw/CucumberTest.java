package es.uniovi.asw;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.junit.Cucumber;
import es.uniovi.asw.model.Voting;

@RunWith(Cucumber.class)
// @RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
// @ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
//@WebAppConfiguration
//@IntegrationTest({ "server.port=0" })
@CucumberOptions(features = "src/test/resources/features")
public class CucumberTest{

	/**	
	@Given(".+voting with description '(.+)'")
	public void addNewVoting(final String description) {
		Voting vot = new Voting(description);
		//adding was done in rest
	}
	@When("^the user access the main page of the application")
	public void enterApp() {}
*/
	
}