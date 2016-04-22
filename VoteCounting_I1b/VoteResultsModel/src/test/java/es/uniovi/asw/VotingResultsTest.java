package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import es.uniovi.asw.model.VotingResults;

public class VotingResultsTest {

	private VotingResults votRes = new VotingResults();
	
	@Test
	public void testVotingResults() {
		votRes=new VotingResults("Test1");
		assertNotNull(votRes);
		assertEquals("Test1", votRes.getVoting());
	}

	@Test
	public void testVoting() {
		votRes.setVoting("Test2");
		assertEquals("Test2", votRes.getVoting());
	}

	@Test
	public void testResults() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Test1", 60);
		map.put("Test2", 2);
		votRes.setResults(map);
		assertNotNull(votRes.getResults());
		assertTrue(60 == votRes.getResults().get("Test1"));
		assertTrue(2 == votRes.getResults().get("Test2"));

	}

}
