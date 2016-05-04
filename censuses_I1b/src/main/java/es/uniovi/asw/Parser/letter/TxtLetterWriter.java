package es.uniovi.asw.Parser.letter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import es.uniovi.asw.DBUpdate.model.Voter;

public class TxtLetterWriter implements LetterWriter {
	
	@Override
	public void write(Voter v, String file) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(file + ".txt", "UTF-8");
        writer.println("Dear " + v.getName() + ", here's your voting information");
        writer.println("Voting Station: " + v.getStationCode());
        writer.println("Password: " + v.getPass());
        writer.close();
	}

}
