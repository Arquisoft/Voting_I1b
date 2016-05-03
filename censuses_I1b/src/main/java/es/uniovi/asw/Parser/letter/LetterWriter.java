package es.uniovi.asw.Parser.letter;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import es.uniovi.asw.DBUpdate.model.Voter;

public interface LetterWriter {
	public void write(Voter v, String file) throws FileNotFoundException, UnsupportedEncodingException;
}
