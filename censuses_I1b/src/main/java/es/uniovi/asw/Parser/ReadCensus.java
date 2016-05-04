package es.uniovi.asw.Parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import es.uniovi.asw.UpdateDB.IInsert;
import es.uniovi.asw.UpdateDB.Insert;
import es.uniovi.asw.UpdateDB.model.Voter;
import es.uniovi.asw.Parser.letter.LetterWriter;
import es.uniovi.asw.Parser.letter.TxtLetterWriter;
import es.uniovi.asw.Parser.parser.Parser;
import es.uniovi.asw.Parser.parser.XSSFParser;

public class ReadCensus implements IReadCensus {

	private Parser reader;
	private final static String PATH = "letters/";
	private List<Voter> voters;
	private LetterWriter writer;
	private IInsert db;

	public ReadCensus() {
		RCensus();
	}

	public void RCensus(){
		this.reader = new XSSFParser();
		this.db = new Insert();
		this.writer = new TxtLetterWriter();
	}
	
	
	@Override
	public List<Voter> read(String file) {
		voters = null;
		try {
			voters = reader.processFile(file);
			generatePasswords();
			writeLetters();
			try{
				InserR(voters);
				db.insert(voters);
			} catch(IllegalStateException e){
				System.out.println(e.getMessage());
			}
		} catch (IOException e) {
			System.out.println("The file '" + file + "' was not found");
		}
		return voters;
	}

	private void generatePasswords() {
		for (Voter voter : voters) {
			voter.setPass(RandomPassGenerator.getRandomPass());
		}
	}

	private void writeLetters() {
		for (Voter voter : voters) {
			String filename = PATH + voter.getDni();

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

	@Override
	public List<Voter> InserR(List<Voter> voters) {
		if(voters == null || voters.isEmpty()){
			throw new IllegalStateException("There are no voters to insert");
		}
		return voters;
	}

}
