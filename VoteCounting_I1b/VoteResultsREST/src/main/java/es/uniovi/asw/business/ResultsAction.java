package es.uniovi.asw.business;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.factories.Factories;
import es.uniovi.asw.model.Vote;
import es.uniovi.asw.model.Voting;
import es.uniovi.asw.model.VotingResults;

@Service
public class ResultsAction {
	@Autowired
	 Factories factories;
	public ResultsAction(){}
	
	public VotingResults execute(long id) {
		try {
			
			Voting actualVoting = factories.getPersistenceFactory().getVotingRepository().findOne(id);
			VotingResults votingRes = new VotingResults(actualVoting.getDescription());
			for (Vote vote : actualVoting.getVotes()) {
				String option = vote.getOption();
				Map<String, Integer> map = votingRes.getResults();
				if (map.containsKey(option))
					map.replace(option, map.get(option) + 1);
				else
					map.put(option, 1);
			}
			return votingRes;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new VotingResults("");
	}

}
