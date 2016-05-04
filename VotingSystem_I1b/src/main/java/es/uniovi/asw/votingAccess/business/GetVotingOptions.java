package es.uniovi.asw.votingAccess.business;

import java.util.ArrayList;
import java.util.List;

/**
 * Gets a list of options, the voting options
 * @author const
 *
 */
public class GetVotingOptions {

	
	/**
	 * Gets the list of voting options
	 * @return a list of voting options
	 */
	public List<String> getVotingOptions() {
		List<String> options = new ArrayList<String>();
		options.add("Yes");
		options.add("No");
		return options;
	}

}
