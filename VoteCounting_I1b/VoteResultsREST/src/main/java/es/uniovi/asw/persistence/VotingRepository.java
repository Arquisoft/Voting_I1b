package es.uniovi.asw.persistence;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import es.uniovi.asw.model.Voting;

@Repository
public interface VotingRepository extends CrudRepository<Voting, Long> {


}
