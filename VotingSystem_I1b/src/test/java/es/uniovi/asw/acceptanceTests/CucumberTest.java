package es.uniovi.asw.acceptanceTests;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import es.uniovi.asw.DBUpdate.DatabaseTestHelper;

@RunWith(Cucumber.class)
// @RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
// @ContextConfiguration(classes = Application.class, loader = SpringApplicationContextLoader.class)
//@WebAppConfiguration
//@IntegrationTest({ "server.port=0" })
@CucumberOptions(features = "src/test/resources/features")
public class CucumberTest {
	
	
}