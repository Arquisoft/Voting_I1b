package es.uniovi.asw;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.Voting;

public class VoteTest {
	
	private Vote vote= new Vote();	
	private Voting voting = new Voting();

	@Test
	public void testVote() {
		vote = new Vote(1,voting , "Si");
		assertNotNull(vote);
		assertEquals(vote.getOption(), "Si");
		assertEquals(vote.getVoting(), voting);
		assertTrue(vote.getId()==1);		
	}

	@Test
	public void testOption() {
		vote.setOption("Si");
		assertEquals(vote.getOption(), "Si");
	}

	@Test
	public void testId() {
		vote.setId(1);
		assertTrue(vote.getId()==1);
	}

	@Test
	public void testVoting() {
		vote.setVoting(voting);
		assertEquals(vote.getVoting(), voting);
	}

	@Test
	public void testEqualsObject() {
		Vote vote2 = new Vote(1,voting , "Si");
		vote.setId(1);
		assertEquals(vote, vote2);
		assertNotEquals(vote, null);
		assertNotEquals(vote,new Voting());
		assertNotEquals(vote,new Vote());

	}

	@Test
	public void testToString() {
		vote = new Vote(1, voting, "Si"); 
		assertEquals("Vote [id=" + 1 + ", voting=" + voting + ", option=Si]", vote.toString());
	}

	@Test
	public void testHashCode() {
		assertNotNull(vote.hashCode());
	}
}
