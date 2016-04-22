package es.uniovi.asw.factories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.business.ResultsAction;
import es.uniovi.asw.business.VotingsAction;
import es.uniovi.asw.model.VotingMinimalInfoAdapter;
import es.uniovi.asw.model.VotingResults;
@Service
public class BusinessFactory {

	@Autowired ResultsAction results;
	@Autowired VotingsAction votings;
	
	public VotingResults getResults(long id) {
		
		return results.execute(id);		
	}

	public List<VotingMinimalInfoAdapter> getVotings() {
		return votings.execute();
	}

}
