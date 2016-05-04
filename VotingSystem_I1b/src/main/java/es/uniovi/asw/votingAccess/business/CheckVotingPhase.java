package es.uniovi.asw.votingAccess.business;



public class CheckVotingPhase {

	
	public static final int PRE_VOTING = 0;
	public static final int VOTING = 1;
	public static final int POST_VOTING = 2;
	
	public static int votingPhase = PRE_VOTING;
	
	
	
	public static int checkVotingPhase() {
	
		return votingPhase;
	}
	
	
	
	
	
	
	
}
