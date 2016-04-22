package es.uniovi.asw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uniovi.asw.factories.Factories;
import es.uniovi.asw.model.VotingMinimalInfoAdapter;
import es.uniovi.asw.model.VotingResults;


@Controller
public class Main {

  @Autowired
  Factories factories;
  
  @RequestMapping(value = "/voting", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<VotingResults> getVotingInfo(@RequestParam("id") long id) {
	  
  	return  new ResponseEntity<VotingResults>(factories.getBusinessFactory().getResults(id), HttpStatus.OK);
  	}
  @RequestMapping(value = "/votings", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<List<VotingMinimalInfoAdapter>> getVotings() {
	  
  	return  new ResponseEntity<List<VotingMinimalInfoAdapter>>(factories.getBusinessFactory().getVotings(), HttpStatus.OK);
  	}
}