package es.uniovi.asw.Parser;

import java.io.IOException;
import java.util.List;

import es.uniovi.asw.DBUpdate.model.Voter;

public class ReadCensus implements IReadCensus {

	private Reader reader;

	public ReadCensus(Reader reader) {
		this.reader = reader;
	}

	@Override
	public List<Voter> read(String file) {
		List<Voter> voters = null;
		try {
			voters = reader.read(file);
		} catch (IOException e) {
			System.out.println("The file '" + file + "' was not found");
		}
		return voters;
	}

}
