package es.uniovi.asw.DBUpdate;

import java.util.List;

import es.uniovi.asw.DBUpdate.model.Voter;

public class Insert implements IInsert {

	@Override
	public void InsertP(List<Voter> voters) {
		InsertVoters.checkMissingFields(voters);
	}

	@Override
	public void insert(List<Voter> voters) {
		try {
			InsertP(voters);
			InsertVoters.insert(voters);
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
		}
	}

}
