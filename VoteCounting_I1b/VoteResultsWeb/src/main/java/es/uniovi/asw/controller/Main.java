package es.uniovi.asw.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import es.uniovi.asw.model.Voting;
import es.uniovi.asw.model.VotingResults;


@Controller
public class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  @RequestMapping("/")
  public String landing(Model model) {
    LOG.info("Landing page access");
    RestTemplate rt = new RestTemplate();
    Voting[] response = rt.getForObject("http://localhost:8080/votings", Voting[].class);
    model.addAttribute("response",response);
    return "landing";
  }
  @RequestMapping(value = "/voting" , params = {"id"})
  public String ids(Model model,@RequestParam(value = "id") Long id ) {
    LOG.info("Votations page");
    RestTemplate rt = new RestTemplate();
    VotingResults response2 = rt.getForObject("http://localhost:8080/voting?id={id}", VotingResults.class, id);
    model.addAttribute("response2",response2);
    return "ids";
  }
  
  
}