package es.uniovi.asw.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import es.uniovi.asw.DBUpdate.modelo.Vote;
import es.uniovi.asw.DBUpdate.modelo.Voter;
import es.uniovi.asw.votingAccess.business.CheckVotingPhase;
import es.uniovi.asw.votingAccess.business.GetVotingOptions;
import es.uniovi.asw.votingAccess.business.LogInEVoter;
import es.uniovi.asw.votingAccess.business.RegisterEVoter;
import es.uniovi.asw.votingAccess.business.VoteEVoter;
import es.uniovi.asw.votingAccess.exception.BusinessException;
import es.uniovi.asw.votingAccess.exception.BusinessExceptionMessages;

@RestController
public class Controller {
	
	Voter loggedVoter;
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(Model model, @RequestParam("inputNIF") String nif) throws SQLException {
		try {
			new RegisterEVoter().registerEVoter(nif);
		} catch (BusinessException b) {
			String errorAttribute = "";
			if (b.getMessage().equals(BusinessExceptionMessages.NOT_IN_CENSUS))
				errorAttribute = "notInCensus";
			else if (b.getMessage().equals(BusinessExceptionMessages.ALREADY_EVOTER))
				errorAttribute = "alreadyEVoter";
			model.addAttribute(errorAttribute, true);
			return new ModelAndView("register");
		}
		model.addAttribute("nifRegistered", true);
		return new ModelAndView("register");
	}
	
	
	@RequestMapping(value = "/")
	public ModelAndView landing(Model model) {
		if (new CheckVotingPhase().checkVotingPhase() == CheckVotingPhase.VOTING)
			return new ModelAndView("login");
		else if (new CheckVotingPhase().checkVotingPhase() == CheckVotingPhase.PRE_VOTING)
			return new ModelAndView("register");
		else
			return new ModelAndView("test");
	}
	
	
	@RequestMapping(value = "/vote", method = RequestMethod.GET)
	public ModelAndView vote(Model model, @RequestParam("inputVote") String vote, HttpServletRequest request)
			throws SQLException {
		
		Vote submitVote = new Vote(vote);
		new VoteEVoter().voteEVoter(submitVote, loggedVoter);
			
		return new ModelAndView("voted");
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model, @RequestParam("inputNIF") String nif,
			@RequestParam("inputPassword") String password, HttpServletRequest request) throws SQLException {
		
		Voter voter = null;
		String errorAttribute = null;
		try {
			voter = new LogInEVoter().logInEVoter(nif, password);
		} catch (BusinessException b) {
			if (b.getMessage().equals(BusinessExceptionMessages.NOT_IN_CENSUS))
				errorAttribute = "notInCensus";
			else if (b.getMessage().equals(BusinessExceptionMessages.WRONG_PASSWORD))
				errorAttribute = "wrongPassword";
			else if (b.getMessage().equals(BusinessExceptionMessages.NOT_EVOTER))
				errorAttribute = "notEVoter";
			else if (b.getMessage().equals(BusinessExceptionMessages.HAS_ALREADY_VOTED))
				errorAttribute = "alreadyVoted";
			model.addAttribute(errorAttribute, true);
			return new ModelAndView("login");
		}
		loggedVoter = voter;
		model.addAttribute("votingOptions", new GetVotingOptions().getVotingOptions());
		return new ModelAndView("vote");
	}

}
