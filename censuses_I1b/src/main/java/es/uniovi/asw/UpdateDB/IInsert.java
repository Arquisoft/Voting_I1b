package es.uniovi.asw.UpdateDB;

import java.util.List;

import es.uniovi.asw.UpdateDB.model.Voter;

public interface IInsert {
	public void insert(List<Voter> voters);
	void InsertP(List<Voter> voters);
}
