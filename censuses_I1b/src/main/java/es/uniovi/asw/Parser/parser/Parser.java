package es.uniovi.asw.Parser.parser;

import java.io.IOException;
import java.util.List;

import es.uniovi.asw.UpdateDB.model.Voter;

public interface Parser {
	List<Voter> processFile(String file) throws IOException;
}
