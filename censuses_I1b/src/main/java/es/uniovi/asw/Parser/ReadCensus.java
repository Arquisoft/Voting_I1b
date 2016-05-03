package es.uniovi.asw.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import es.uniovi.asw.DBUpdate.IInsert;
import es.uniovi.asw.DBUpdate.Insert;
import es.uniovi.asw.DBUpdate.model.Voter;
import es.uniovi.asw.Parser.letter.LetterWriter;
import es.uniovi.asw.Parser.letter.TxtLetterWriter;
import es.uniovi.asw.Parser.reader.Reader;

public class ReadCensus implements IReadCensus {

	private Reader reader;
	private final static String PATH = "letters/";
	private List<Voter> voters;
	private IInsert db = new Insert();

	public ReadCensus(Reader reader) {
		this.reader = reader;
	}

	@Override
	public List<Voter> read(String file) {
		voters = null;
		try {
			voters = reader.read(file);
			generatePassword();
			db.insert(voters);
			writeLetter();
		} catch (IOException e) {
			System.out.println("The file '" + file + "' was not found");
		}
		return voters;
	}

	private void generatePassword() {
		for (Voter voter : voters) {
			voter.setPass(RandomPassGenerator.getRandomPass());
		}
	}

	private void writeLetter() {
		for (Voter voter : voters) {
			String filename = PATH + voter.getDni();

			LetterWriter writer = new TxtLetterWriter();
			try {
				writer.write(voter, filename);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
