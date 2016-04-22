package es.uniovi.asw.persistence;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.uniovi.asw.model.Vote;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {


}
