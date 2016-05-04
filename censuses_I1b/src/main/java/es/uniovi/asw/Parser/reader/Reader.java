package es.uniovi.asw.Parser.reader;

import java.io.IOException;
import java.util.List;

import es.uniovi.asw.DBUpdate.model.Voter;

public interface Reader {
	List<Voter> processFile(String file) throws IOException;
}
