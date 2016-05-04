package es.uniovi.asw.Parser;

import java.util.List;

import es.uniovi.asw.DBUpdate.model.Voter;

public interface IReadCensus {
	public List<Voter> read(String file);
	public void RCensus();
	public List<Voter> InserR(List<Voter> voters);
}
