package es.uniovi.asw.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.persistence.VoteRepository;
import es.uniovi.asw.persistence.VotingRepository;

@Service
public class PersistenceFactory {
	
	@Autowired
	VoteRepository votRep;
	@Autowired
	VotingRepository votingRep;
	 
	public VoteRepository getVoteRepository()
	{
		return votRep;
	}
	
	public VotingRepository getVotingRepository()
	{
		return votingRep;
	}
}
