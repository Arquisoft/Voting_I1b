package es.uniovi.asw.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uniovi.asw.factories.Factories;
import es.uniovi.asw.model.Voting;
import es.uniovi.asw.model.VotingMinimalInfoAdapter;

@Service
public class VotingsAction {
	
	@Autowired
	Factories factories;
	
	public VotingsAction(){}	
	
	public List<VotingMinimalInfoAdapter> execute() {
		List<VotingMinimalInfoAdapter> votings = new ArrayList<>();
		try {			
			for (Voting voting : factories.getPersistenceFactory().getVotingRepository().findAll())
				votings.add(new VotingMinimalInfoAdapter(voting));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return votings;
	}

}
