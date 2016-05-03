package es.uniovi.asw.Parser;

import java.io.IOException;
import java.util.List;

import es.uniovi.asw.DBUpdate.model.Voter;

public interface Reader {
	public List<Voter> read(String file) throws IOException;
	List<Voter> processFile(String file) throws IOException;
}
