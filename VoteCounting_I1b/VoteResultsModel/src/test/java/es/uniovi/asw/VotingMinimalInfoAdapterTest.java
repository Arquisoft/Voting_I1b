package es.uniovi.asw;

import static org.junit.Assert.*;

import org.junit.Test;

import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.Voting;
import es.uniovi.asw.model.VotingMinimalInfoAdapter;

public class VotingMinimalInfoAdapterTest {

	private Voting voting = new Voting("Test");
	private VotingMinimalInfoAdapter votingAdap = new VotingMinimalInfoAdapter();
	
	@Test
	public void testVotingMinimalInfoAdapter() {
		votingAdap = new VotingMinimalInfoAdapter(voting);
		assertNotNull(votingAdap);
		assertEquals(voting.getDescription(), votingAdap.getDescription());
		assertTrue(voting.getId()==votingAdap.getId());
	}
	@Test
	public void testId() {
		votingAdap.setId(1L);
		assertTrue(1==votingAdap.getId());
	}
	@Test
	public void testEqualsObject() {
		VotingMinimalInfoAdapter v2 = new VotingMinimalInfoAdapter();
		v2.setId(1L);
		votingAdap.setId(1L);
		assertEquals(votingAdap,v2);
		votingAdap.setId(2L);
		assertNotEquals(votingAdap,v2);
		assertNotEquals(votingAdap,null);
		assertNotEquals(votingAdap,new Vote());
		assertNotEquals(votingAdap, new VotingMinimalInfoAdapter());

	}
	@Test
	public void testDescription() {
		votingAdap.setDescription("Test2");
		assertEquals("Test2", votingAdap.getDescription());
	}
	@Test
	public void testHashCode() {
		assertNotNull(votingAdap.hashCode());
	}

}
