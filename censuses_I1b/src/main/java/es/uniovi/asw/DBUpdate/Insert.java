package es.uniovi.asw.DBUpdate;

import java.util.List;

import es.uniovi.asw.DBUpdate.model.Voter;

public class Insert implements IInsert {

	@Override
	public void insert(List<Voter> voters) {
		InsertVoters.insert(voters);
	}

}
