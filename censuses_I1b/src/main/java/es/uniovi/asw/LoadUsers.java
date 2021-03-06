package es.uniovi.asw;

import es.uniovi.asw.UpdateDB.model.Voter;
import es.uniovi.asw.Parser.IReadCensus;
import es.uniovi.asw.Parser.ReadCensus;
import java.util.List;

/**
 * Main application
 * 
 * @author UO236953
 * @author UO238739
 *
 */
public class LoadUsers {

	public static void main(String... args) {
		IReadCensus reader = new ReadCensus();
		List<Voter> voters = reader.read(args[0]);
		if (voters != null) {
			for (Voter v : voters) {
				System.out.println(v.toString());
			}
		}
	}
}
