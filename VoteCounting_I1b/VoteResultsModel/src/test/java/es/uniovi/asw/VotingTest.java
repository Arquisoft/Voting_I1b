/**
 * 
 */
package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.Voting;

/**
 * @author Becario-2
 *
 */
public class VotingTest {
	
	private Voting voting = new Voting();


	@Test
	public void testVoting() {
		voting = new Voting("Test1");
		assertNotNull(voting);
		assertEquals("Test1", voting.getDescription());
	}
	@Test
	public void testId() {
		voting.setId(1L);
		assertTrue(1L== voting.getId());
	}
	@Test
	public void testDescription() {
		voting.setDescription("Test2");
		assertEquals("Test2", voting.getDescription());
	}
	@Test
	public void testVotes() {
		voting.setVotes(new HashSet<>(Arrays.asList(new Vote[]{new Vote(1,voting, "Si"),new Vote(2,voting, "No"), new Vote(3,voting, "No")})));
		assertTrue(voting.getVotes().size()== 3);
	}
	
	@Test
	public void testEqualsObject() {
		Voting v2 = new Voting();
		v2.setId(1L);
		voting.setId(1L);
		assertEquals(voting,v2);
		assertNotEquals(voting,null);
		assertNotEquals(voting,new Vote());
		assertNotEquals(voting,new Voting());

	}
	@Test
	public void testHashCode() {
		assertNotNull(voting.hashCode());
	}
}
