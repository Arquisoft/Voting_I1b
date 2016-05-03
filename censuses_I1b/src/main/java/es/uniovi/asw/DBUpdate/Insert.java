package es.uniovi.asw.DBUpdate;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import es.uniovi.asw.DBUpdate.model.Voter;

public class Insert implements IInsert {

	@Override
	public void insert(List<Voter> voters) {
		try {
			InsertVoters.insert(voters);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
